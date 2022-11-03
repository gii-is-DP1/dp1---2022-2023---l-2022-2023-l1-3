package org.springframework.samples.petclinic.game;

import java.util.Collection;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Admin;
import org.springframework.samples.petclinic.board.Board;
import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.player.Player;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "game")
public class Game extends BaseEntity {

    @Column(name="num_players")
    @NotNull
    @Min(1)
    @Max(4)
    private Integer numPlayers;
    
    @Column(name = "state")
    @Enumerated(EnumType.STRING)
    @NotNull
    private GameState gameState = GameState.ABIERTA;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    @NotNull
    private GameType gameType;

    @Column(name = "isPublic")
    @NotNull
    private Boolean isPublic = true;

    @ManyToMany(mappedBy = "games")
    Collection<Player> players;

    @ManyToMany(mappedBy = "games")
    Collection<Admin> admins;


    @OneToOne(optional = true)
    @NotNull
    private Board board;

}
