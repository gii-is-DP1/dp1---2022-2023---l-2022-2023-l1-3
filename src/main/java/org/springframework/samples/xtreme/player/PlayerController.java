package org.springframework.samples.xtreme.player;


import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.xtreme.game.Game;
import org.springframework.samples.xtreme.game.GameService;
import org.springframework.samples.xtreme.user.User;
import org.springframework.samples.xtreme.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

@Controller
@RequestMapping(path="/players")
public class PlayerController {

    
    private final PlayerService playerService;
    private final UserService userService;
    private final GameService gameService;

    private final PlayerValidator playerValidator;



    private static final String VIEWS_FORM = "players/createPlayerForm";
    private static final String PLAYERS_LIST = "players/playersList";
    private static final String PROFILE = "players/viewProfile";
    private static final String EDIT_PROFILE = "players/editProfile";
    private static final String ALL_MY_MATCHS= "players/allMyMatchs";

    

    @Autowired
    public PlayerController(PlayerService playerService, PlayerValidator playerValidator,
    UserService userService,GameService gameService) {
        this.playerService = playerService;
        this.playerValidator=playerValidator;
        this.userService=userService;
        this.gameService=gameService;
    }

    
    @InitBinder("player")
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(playerValidator);
    }


    
    @GetMapping
    public ModelAndView showPlayerList() {
        ModelAndView mav = new ModelAndView(PLAYERS_LIST);
        mav.addObject("players", this.playerService.getAllPlayers());
        return mav;
    }



    @GetMapping(path="/create")
    public ModelAndView viewForm(){
        ModelAndView mav = new ModelAndView(VIEWS_FORM);
        mav.addObject("player", new Player());
        return mav;
    }

    @PostMapping(path = "/create")
    public ModelAndView createPlayer(@Valid @ModelAttribute("player") Player player, BindingResult res){
        ModelAndView mav = new ModelAndView("redirect:/");

        if(res.hasErrors()){
            mav = new ModelAndView(VIEWS_FORM);
            mav.addObject("player", player);
        } else{
            playerService.save(player);
        }
        return mav;
    }
    


    @GetMapping(path="/{username}")
    public ModelAndView showProfile(@PathVariable String username){
        ModelAndView mav = new ModelAndView(PROFILE);
        Player player = this.playerService.findByUsername(username);
        mav.addObject("isBanned", player.getUser().isEnabled());
        mav.addObject("player", player);
        // obtener el usuario actualmente logueado
        Object principal=SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails=null;
        Boolean esAdmin = false;
        Boolean esUserEqual = false;
        if (principal instanceof UserDetails) {
            userDetails = (UserDetails) principal;
            esAdmin=userDetails.getAuthorities().stream().anyMatch(x-> x.getAuthority().equals("admin"));
            esUserEqual = userDetails.getUsername().equals(username);
        }
        
        mav.addObject("esAdmin", esAdmin);
        mav.addObject("esUserEqual", esUserEqual);
        return mav;
    }

    @PostMapping(path="/{username}")
    public ModelAndView showProfilePost(@RequestParam String enabled, @PathVariable("username") String username){
        ModelAndView mav = new ModelAndView("redirect:/players");
        
        User user = this.playerService.findByUsername(username).getUser();
        System.out.println("--- el usuario "+ user.getUsername()+" esta: "+ enabled);

       if(enabled.equals("activado")){
            user.setEnabled(true);
        }else{
            user.setEnabled(false);
        }
        this.userService.save(user);
        
        return mav;
    }
    


    @GetMapping(path="/{username}/edit")
    public ModelAndView editProfile(@PathVariable("username") String username){
        ModelAndView mav = new ModelAndView(EDIT_PROFILE);
        Player player = this.playerService.findByUsername(username);
        // obtener el usuario actualmente logueado
        Object principal=SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails=null;
        Boolean esUserEqual = false;
        if (principal instanceof UserDetails) {
            userDetails = (UserDetails) principal;
            esUserEqual = userDetails.getUsername().equals(username);
        }
        mav.addObject("esUserEqual", esUserEqual);
        System.out.println(esUserEqual);
        mav.addObject("player", player);
        return mav;
    }

    @PostMapping(path="/{username}/edit")
    public ModelAndView editProfilePost(@Valid @ModelAttribute("player") Player updatePlayer, BindingResult res, @PathVariable("username") String username){
        ModelAndView mav = new ModelAndView("redirect:/players/{username}");
        
        Player player=this.playerService.findByUsername(username);

        player.setPicProfile(updatePlayer.getPicProfile());
        player.setEmail(updatePlayer.getEmail());
        player.setFirstName(updatePlayer.getFirstName());
        player.setLastName(updatePlayer.getLastName());
        player.getUser().setPassword(updatePlayer.getUser().getPassword());
        //player.getUser().setUsername(updatePlayer.getUser().getUsername());

        this.playerService.save(player);

        return mav;
    
    }
    

    @GetMapping(path="{username}/myMatchPlayed")
    public ModelAndView allMyMatch(@PathVariable("username") String username){
        ModelAndView mav = new ModelAndView(ALL_MY_MATCHS);
        Player p= this.playerService.findByUsername(username);
        List<Game> games=gameService.getAll().stream().filter(x->x.getPlayers().contains(p)).collect(Collectors.toList());
        mav.addObject("games", games);
        mav.addObject("player", p);

        List<Game> gamesWinned=gameService.getAll().stream().filter(x->x.getPlayerWinner() !=null && x.getPlayerWinner().getUser().getUsername().equals(username)).collect(Collectors.toList());
        mav.addObject("gamesWinned", gamesWinned);
        return mav;
    }
}
