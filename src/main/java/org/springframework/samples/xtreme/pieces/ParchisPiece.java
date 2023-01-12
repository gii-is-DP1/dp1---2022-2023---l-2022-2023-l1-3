package org.springframework.samples.xtreme.pieces;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.samples.xtreme.board.ParchisBoard;
import org.springframework.samples.xtreme.cells.ParchisCell;
import org.springframework.samples.xtreme.model.BaseEntity;
import org.springframework.samples.xtreme.player.Player;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="parchis_piece")
public class ParchisPiece extends BaseEntity{

    @ManyToOne(cascade = CascadeType.ALL)
    private ParchisBoard board;

    @ManyToOne(cascade = CascadeType.ALL)
    private ParchisCell cell;

    private Boolean inBase = true;

    @ManyToOne
    private Player player;

    @Column(name="color")
    @Enumerated(EnumType.STRING)
    @NotNull
    private Color color;

    private String name;
    
    public Integer getPosicion(){
        return this.cell.getPosition();
    }

    public Integer getBaseCell() {
        Integer cell;
        if (this.color.equals(Color.YELLOW)) {
            cell = 101;
        } else if (this.color.equals(Color.GREEN)) {
            cell = 102;
        } else if (this.color.equals(Color.RED)) {
            cell = 103;
        } else {
            cell = 104;
        }
        return cell;
    }

    public Integer getStartCell() {
        Integer cell;
        if (this.color.equals(Color.YELLOW)) {
            cell = 5;
        } else if (this.color.equals(Color.GREEN)) {
            cell = 22;
        } else if (this.color.equals(Color.RED)) {
            cell = 39;
        } else {
            cell = 59;
        }
        return cell;
    }


    /*
    public Double getPositionInPixels(Integer size, Double position) {
        return position*size;
    }
    */

}
