package org.springframework.samples.xtreme.game;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.xtreme.player.Player;
import org.springframework.samples.xtreme.player.PlayerService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(path="/games")
public class GameController {

    private final GameService gameService;
    private final PlayerService playerService;

    private static final String CREATE_GAME = "games/createGame";
    private static final String VIEW_GAMES = "games/viewGames";
    private static final String LOBBY_VIEW = "games/lobby";

    @Autowired
    public GameController(GameService gameService, PlayerService playerService){
        this.gameService = gameService;
        this.playerService = playerService;

    }

    @GetMapping(path="/createGame")
    public ModelAndView createGame() {
        ModelAndView mav = new ModelAndView(CREATE_GAME);
        mav.addObject("game",new Game());
      
        return mav;
    }

    @PostMapping(path = "/createGame")
    public ModelAndView createGame(@Valid Game game, BindingResult res){
        ModelAndView mav = new ModelAndView("redirect:/"+CREATE_GAME);
       
        Object principal=SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails=null;
        if (principal instanceof UserDetails) {
            userDetails = (UserDetails) principal;
            Player player = playerService.findByUsername(userDetails.getUsername());
            game.setCreatorPlayer(player);

            game.addPlayerToGame(player);
          }

        if(res.hasErrors()||game.getGameName()==null||game.getCreatorPlayer()==null){
            mav = new ModelAndView(CREATE_GAME);
            mav.addObject("game", game);
            
        }else{
            gameService.save(game);
            mav = new ModelAndView("redirect:/"+LOBBY_VIEW+"/"+game.getId());
        }
        return mav;
    }

    @GetMapping(path="/lobby/{id}")
    public ModelAndView lobby(@PathVariable Integer id) {
        ModelAndView mav = new ModelAndView(LOBBY_VIEW);
        mav.addObject("game",gameService.findGameById(id).get());
        mav.addObject("lista", gameService.findGameById(id).get().getPlayers());
        return mav;
    }
    
    @GetMapping(path = "/joinGame")
    public ModelAndView listingGames(){
        ModelAndView mav = new ModelAndView(VIEW_GAMES);
        Collection<Game> games = gameService.getAll();
        List<Game> filter = games.stream().filter(x -> x.getIsPublic() == true && x.getStateGame().equals(StateGame.WAITING_PLAYERS)).collect(Collectors.toList());
        
        mav.addObject("games",filter);
        return mav;
    }

    @PostMapping(path = "/joinGame")
    public ModelAndView joinGame(@Valid @ModelAttribute("game") Game game, BindingResult res){
        ModelAndView mav = new ModelAndView("redirect:/users/home");

        Object principal=SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails) principal;
        String usuarioActual = userDetails.getUsername();

        Player player = playerService.findByUsername(usuarioActual);
        game.getPlayers().add(player);
        gameService.save(game);

        return mav;
        
    }




    
}
