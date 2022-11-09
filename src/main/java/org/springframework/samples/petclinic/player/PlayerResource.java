package org.springframework.samples.petclinic.player;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.player.Player;
import org.springframework.samples.petclinic.player.PlayerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping(path="/players")
public class PlayerResource {

    private final PlayerService playerService;
    private static final String VIEWS_FORM = "players/createPlayerForm";
    private static final String USERS_LIST = "players/playersList";
    private static final String USERS_LOGIN = "players/loginPlayerForm";

    @Autowired
    public PlayerResource(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping
    public ModelAndView showPlayerList() {
        ModelAndView mav = new ModelAndView(USERS_LIST);
        mav.addObject("players", this.playerService.getAllPlayers());
        return mav;
    }

    @GetMapping(path="/create")
    public String viewForm(ModelMap map){
        String view = VIEWS_FORM;
        map.addAttribute("player", new Player());
        return view;
    }

    @PostMapping(path = "/create")
    public String createProduct(@Valid Player player, BindingResult res, ModelMap map){
        String view = "welcome";
        if(res.hasErrors()){
            map.addAttribute("player", player);
            return VIEWS_FORM;
        }else{
           playerService.save(player);
            map.addAttribute("message", "Player succesfully save");
        }
        return view;
    }

    /* 
    @GetMapping("/login")
    public String showForm(ModelMap map) {
        String view = USERS_LOGIN;
        map.put("loginForm", new LoginForm());
        return view;
    }

    @PostMapping("/login")
    public String validateLoginInfo(ModelMap map, @Valid LoginForm loginForm, BindingResult bindingResult) {
        String view = "";
        if (bindingResult.hasErrors()) {
            map.put("loginForm", loginForm);
            view = USERS_LOGIN;
        }
        Player p = playerService.findByUsername(loginForm.getUserName());

        if (p!=null && p.getPassword().equals(loginForm.getPassword())){
            view = "redirect:/players";
        }

        return view;
    }

    */


    @GetMapping("/login")
    public ModelAndView userLogin() {
        ModelAndView mav = new ModelAndView(USERS_LOGIN);
        mav.addObject("loginForm", new LoginForm());
        return mav;
    }

    @PostMapping("/login")
    public ModelAndView processLoginForm(@Valid LoginForm loginForm, BindingResult result) {
        ModelAndView mav = new ModelAndView("redirect:/players");
        Player p = playerService.findByUsername(loginForm.getUsername());
        if (result.hasErrors() || !(p!=null && p.getPassword().equals(loginForm.getPassword()))) {
            mav = new ModelAndView(USERS_LOGIN);
            mav.addObject("loginForm", loginForm);
            mav.addObject("message", "El usuario o la contraseña no son correctos");
        }
        return mav;
    }
    
}
