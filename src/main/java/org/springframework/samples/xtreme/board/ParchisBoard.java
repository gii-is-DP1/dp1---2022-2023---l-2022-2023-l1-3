package org.springframework.samples.xtreme.board;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Positive;

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

    String background;

    @Positive
    int width;

    @Positive
    int height;

    public ParchisBoard() {
        this.background="resources/img/boards/parchis/parchisBoard.svg";
        this.width=800;
        this.height=800;
    }

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "board", fetch = FetchType.EAGER)
    private List<ParchisPiece> pieces;
    
}
