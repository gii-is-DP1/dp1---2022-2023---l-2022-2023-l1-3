package org.springframework.samples.petclinic.casilla;


import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.samples.petclinic.board.Board;
import org.springframework.samples.petclinic.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Casilla extends BaseEntity{
    
    @NotNull
    @Size(min=0, max=105)
    private Integer position;

    @ManyToOne
    @NotNull
    private Board board;
}
