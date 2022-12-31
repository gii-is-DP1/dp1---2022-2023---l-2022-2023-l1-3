package org.springframework.samples.xtreme.turn;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.springframework.samples.xtreme.model.BaseEntity;
import org.springframework.samples.xtreme.player.Player;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ParchisTurn extends BaseEntity {
    
    private Integer turn;
    private Integer playerIndex;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Player> players;

    @OneToOne
    private Player currentPlayer;

    private Boolean isThrown;


    public void TurnInit(List<Player> players) {
        this.turn = 0;
        this.playerIndex = 0;
        this.players = List.copyOf(players);
        Collections.shuffle(this.players);
        this.currentPlayer = players.get(playerIndex);
        this.isThrown = false;
    }

    public void NextTurn() {
        this.playerIndex = this.playerIndex++ % players.size();
        this.currentPlayer = players.get(playerIndex);
        this.isThrown = false;
    }
}
