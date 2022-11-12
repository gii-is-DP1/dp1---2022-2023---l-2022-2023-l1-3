package org.springframework.samples.xtreme.pieces;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.samples.xtreme.board.ParchisBoard;
import org.springframework.samples.xtreme.cells.ParchisCell;
import org.springframework.samples.xtreme.model.BaseEntity;
import org.springframework.samples.xtreme.player.Player;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="parchisPiece")
public class ParchisPiece extends BaseEntity{

    @ManyToOne(cascade = CascadeType.ALL)
    private ParchisBoard board;

    @ManyToOne(cascade = CascadeType.ALL)
    private ParchisCell cell;

    private Boolean start=true;

    @ManyToOne
    private Player player;

    private Color color;

    public Integer getPosicion(){
        return this.cell.getPosition();
    }

    public Integer casillaCasa() {
        Integer s;
        if(this.color==Color.BLUE) {
            s= 101;
        }else if(this.color==Color.GREEN) {
            s= 102;
        }else if(this.color==Color.YELLOW) {
            s= 103;
        }else {
            s= 104;
        }
        return s;
    }

    public Integer casillaSalida() {
        Integer s;
        if(this.color==Color.BLUE) {
            s= 22;
        }else if(this.color==Color.GREEN) {
            s= 56;
        }else if(this.color==Color.YELLOW) {
            s= 5;
        }else {
            s= 39;
        }
        return s;
    }


    
}
