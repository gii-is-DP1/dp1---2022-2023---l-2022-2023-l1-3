package org.springframework.samples.petclinic.ficha;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.samples.petclinic.board.Board;
import org.springframework.samples.petclinic.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Ficha extends BaseEntity{
    
    @NotNull
    @Size(min=0,max=105)
    private Integer position;

    @NotEmpty
    private String color; // type

    @ManyToOne
    @NotNull
    @JoinColumn(name="board_id")
    private Board board;
    
}
