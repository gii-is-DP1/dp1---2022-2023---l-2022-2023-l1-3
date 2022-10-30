package org.springframework.samples.petclinic.web;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.player.Player;
import org.springframework.samples.petclinic.player.PlayerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Controller
@RequestMapping(path="/api")
@CrossOrigin(origins="http://localhost:8080/")
public class PlayerResource {

    private final PlayerService playerService;

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
    
}
