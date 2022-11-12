package org.springframework.samples.xtreme.board;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.samples.xtreme.cells.ParchisCell;
import org.springframework.samples.xtreme.model.BaseEntity;
import org.springframework.samples.xtreme.pieces.ParchisPiece;

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
