package org.springframework.samples.petclinic.board;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.samples.petclinic.game.Game;
import org.springframework.samples.petclinic.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Board extends BaseEntity{
    // id , num casillas, relacion 1 1 con partida
    
    @NotEmpty
    @Size(min=0, max=105)
    @Column(name="numCasillas")
    private Integer numCasillas;

    @OneToOne(optional = false)
    @NotNull
    private Game game;
}

