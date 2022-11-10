package org.springframework.samples.xtreme.game;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

import org.springframework.samples.xtreme.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class GameName extends BaseEntity{
    
    @NotEmpty
    private String nombreJuego; // PARCHIS, OCA

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;
}