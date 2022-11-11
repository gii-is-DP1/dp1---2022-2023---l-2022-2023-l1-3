package org.springframework.samples.petclinic.board;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.samples.petclinic.cells.ParchisCell;
import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.pieces.ParchisPiece;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="parchisBoard")
public class ParchisBoard extends BaseEntity{

    @OneToMany
    private List<ParchisPiece> pieces;




    
}
