package org.springframework.samples.petclinic.web;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.player.Player;
import org.springframework.samples.petclinic.player.PlayerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Controller
@RequestMapping("/players")
public class PlayerResource {

    private final PlayerService playerService;
    private static final String PLAYER_LISTING = "PlayerListing";

    @Autowired
    public PlayerResource(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping
    public String getPlayers(ModelMap model) {
        List<Player> players = this.playerService.getAll().stream().collect(Collectors.toList());
        model.put("players", players);
        return PLAYER_LISTING;
    }
    
}
