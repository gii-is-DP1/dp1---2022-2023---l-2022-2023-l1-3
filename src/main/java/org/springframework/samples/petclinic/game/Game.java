package org.springframework.samples.petclinic.game;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.tablero.Tablero;

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

    @OneToMany(mappedBy = "player")
    Set<GamePlayerRecord> players;

    @OneToOne(cascade= CascadeType.ALL)
    @JoinColumn(name="tablero_id")
    private Tablero tablero;

}
