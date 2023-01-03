package org.springframework.samples.xtreme.board;

import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.spi.ObjectThreadContextMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.xtreme.game.Game;
import org.springframework.samples.xtreme.game.GameService;
import org.springframework.samples.xtreme.player.Player;
import org.springframework.samples.xtreme.player.PlayerService;
import org.springframework.samples.xtreme.turn.ParchisTurn;
import org.springframework.samples.xtreme.turn.ParchisTurnService;
import org.springframework.samples.xtreme.util.GameUtils;
import org.springframework.samples.xtreme.util.UserUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(path="/parchis")
public class ParchisController {

    private static final String HOME = "home";
    private static final String GAME_VIEW = "parchis/parchisView";
    private static final String GAME_PLAY = "parchis/parchisPlay";
    
    private final ParchisBoardService parchisBoardService;
    private final GameService gameService;
    private final PlayerService playerService;
    private final ParchisTurnService parchisTurnService;

    private UserUtils userUtils = new UserUtils();
    private GameUtils gameUtils = new GameUtils();

    @Autowired
    public ParchisController(ParchisBoardService parchisBoardService, GameService gameService, PlayerService playerService, ParchisTurnService parchisTurnService) {
        this.parchisBoardService = parchisBoardService;
        this.gameService = gameService;
        this.playerService = playerService;
        this.parchisTurnService = parchisTurnService;
    }

    @GetMapping(path = "")
    public ModelAndView gameTest(HttpServletResponse response) {
        response.addHeader("Refresh", "10");
        ModelAndView mav = new ModelAndView("game/parchisBoard");
        mav.addObject("board", parchisBoardService.findById(1).get());
        return mav;
    }

    @GetMapping(path = "/{id}")
    public ModelAndView gameRoom(@PathVariable Integer id, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView(HOME);
        Optional<Game> currentGame = gameService.findGameById(id);
        UserDetails currentUser = userUtils.getUserDetails();
        ParchisTurn currentParchisTurn = parchisTurnService.findByGame(id);
        Player player = playerService.findByUsername(currentUser.getUsername());
        Boolean gameExists = gameUtils.gameExists(gameService.getAll().stream().toList(), currentGame.get());
        Boolean playerInGame = gameUtils.playerInGame(currentGame.get(), player);

        if (gameExists && playerInGame) {
            if (currentParchisTurn.getCurrentPlayer().equals(player)) {
                mav.setViewName("redirect:/parchis/"+id.toString()+"/turn");
            } else {
                mav.setViewName(GAME_VIEW);
            }
        }
        
        return mav;
    }

    @GetMapping(path = "/{id}/turn")
    public ModelAndView turnView(@PathVariable Integer id) {
        ModelAndView mav = new ModelAndView(GAME_PLAY);
        return mav;
    }

}
