package org.springframework.samples.xtreme.board;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Positive;

import org.springframework.samples.xtreme.cells.ParchisCell;
import org.springframework.samples.xtreme.game.Game;
import org.springframework.samples.xtreme.model.BaseEntity;
import org.springframework.samples.xtreme.pieces.ParchisPiece;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="parchisBoard")
public class ParchisBoard extends BaseEntity{

    String background;

    @Positive
    Double width;

    @Positive
    Double height;

    public ParchisBoard() {
        this.background="resources/img/boards/parchis/parchisBoard.svg";
        this.width=1151.5;
        this.height=1151.5;
    }

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "board")
    private List<ParchisPiece> parchisPieces;
    
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "board")
    private List<ParchisCell> cells;

    @OneToOne
    @JoinColumn(name="game")
    private Game game;

    public void addParchisPiecesToBoard(ParchisPiece parchisPiece){
        if(this.parchisPieces == null){
            this.parchisPieces = new ArrayList<>();
        }
        
        this.parchisPieces.add(parchisPiece);
    }

    public void removeParchisPiecesToBoard(ParchisPiece parchisPiece){
        if(this.parchisPieces != null){
            this.parchisPieces.remove(parchisPiece);
        }
    }
}
