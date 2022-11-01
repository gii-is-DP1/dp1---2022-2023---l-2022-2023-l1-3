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
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Controller
@RequestMapping(path="/players")
public class PlayerResource {

    private final PlayerService playerService;
    private static final String VIEWS_FORM = "players/createPlayerForm";

    @Autowired
    public PlayerResource(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping(path = "/users/list")
    public Collection<Player> getPlayers(Map<String, Object> model) {

        List<Player> players = playerService.getAll().stream().collect(Collectors.toList());
        model.put("players", players);
        return players;
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

    
}
