package org.springframework.samples.petclinic.game;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.player.Player;

@Entity
public class GamePlayerRecord extends BaseEntity {

    @ManyToOne
    @MapsId("playerId")
    @JoinColumn(name = "player_id")
    Player player;

    @ManyToOne
    @MapsId("gameId")
    @JoinColumn(name = "game_id")
    Game game;
    
}
