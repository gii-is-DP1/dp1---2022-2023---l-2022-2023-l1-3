package org.springframework.samples.xtreme.player;

import java.security.Principal;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.xtreme.friendship.FriendshipService;
import org.springframework.samples.xtreme.game.Game;
import org.springframework.samples.xtreme.game.GameService;
import org.springframework.samples.xtreme.player.Player;
import org.springframework.samples.xtreme.player.PlayerService;
import org.springframework.samples.xtreme.user.Authorities;
import org.springframework.samples.xtreme.user.AuthoritiesService;
import org.springframework.samples.xtreme.user.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

@Controller
@RequestMapping(path="/players")
public class PlayerController {

    private final PlayerService playerService;
    private final AuthoritiesService authoritiesService;
    private final FriendshipService friendshipService;

    private final PlayerValidator playerValidator;
    private final GameService gameService;



    private static final String VIEWS_FORM = "players/createPlayerForm";
    private static final String PLAYERS_LIST = "players/playersList";
    private static final String VIEW_GAMEHOME = "players/gameHome";
    private static final String CREATE_GAME = "players/createGame";
    private static final String FRIENDS = "players/friends";
    private static final String PROFILE = "players/viewProfile";
    private static final String EDIT_PROFILE = "players/editProfile";

    @Autowired
    public PlayerController(PlayerService playerService,AuthoritiesService authoritiesService,
    FriendshipService friendshipService, PlayerValidator playerValidator, GameService gameService) {
        this.playerService = playerService;
        this.authoritiesService = authoritiesService;
        this.friendshipService=friendshipService;
        this.playerValidator=playerValidator;
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
    
    @GetMapping(path="/createGame")
    public ModelAndView createGame() {
        ModelAndView mav = new ModelAndView(CREATE_GAME);
        mav.addObject("game",new Game());
      
        return mav;
    }

    @PostMapping(path = "/createGame")
    public ModelAndView createGame(@Valid Game game, BindingResult res){
        ModelAndView mav = new ModelAndView("redirect:/lobby");
       
        Object principal=SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails=null;
        if (principal instanceof UserDetails) {
            userDetails = (UserDetails) principal;
            Player player = playerService.findByUsername(userDetails.getUsername());
            game.setCreatorPlayer(player);
          }

        if(res.hasErrors()||game.getGameName()==null||game.getCreatorPlayer()==null){
            mav = new ModelAndView(CREATE_GAME);
            mav.addObject("game", game);
            
        }else{
            gameService.save(game);
        }
        return mav;
    }
    
    @GetMapping(path = "/friends")
    public ModelAndView friends(){
        ModelAndView mav= new ModelAndView(FRIENDS);

        // obtener el usuario actualmente logueado
        Object principal=SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails=null;
        if (principal instanceof UserDetails) {
            userDetails = (UserDetails) principal;
            System.out.println("---Nombre actual del usuario logueado: "+userDetails.getUsername());
            }
        if(userDetails!= null){
        mav.addObject("myfriends", friendshipService.getAcceptedFriendshipsByUsername(userDetails.getUsername()));
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
    public ModelAndView showProfilePost(@Valid @ModelAttribute("player") Player player, BindingResult res, @PathVariable("username") String username){
        ModelAndView mav = new ModelAndView("redirect:/users/"+ username);

        if(res.hasErrors()){
            mav = new ModelAndView(PROFILE);
            mav.addObject("isBanned", player.getUser().isEnabled());
            mav.addObject("player", player);
        } else{
            playerService.save(player);
        }
        return mav;
    }
    
    @GetMapping(path="/{username}/edit")
    public ModelAndView editProfile(@PathVariable("username") String username){
        ModelAndView mav = new ModelAndView(EDIT_PROFILE);
        Player player = this.playerService.findByUsername(username);
        mav.addObject("player", player);
        return mav;
    }

    @PostMapping(path="/{username}/edit")
    public ModelAndView editProfilePost(@Valid @ModelAttribute("player") Player player, BindingResult res, @PathVariable("username") String username){
        ModelAndView mav = new ModelAndView("redirect:/users/"+ username);

        if(res.hasErrors()){
            mav = new ModelAndView(EDIT_PROFILE);
            mav.addObject("player", player);
        } else{
            playerService.save(player);
        }
        return mav;
    }
    
}
