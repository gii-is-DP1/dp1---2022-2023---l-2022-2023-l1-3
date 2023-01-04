package org.springframework.samples.xtreme.board;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Positive;

import org.springframework.samples.xtreme.model.BaseEntity;
import org.springframework.samples.xtreme.oca.OcaPiece;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="ocaBoard")
public class OcaBoard extends BaseEntity{

    String background;

    @OneToMany
    private List<OcaPiece> pieces;
    
    @Positive
    int width;

    @Positive
    int height;

    public OcaBoard() {
        this.background="resources/img/boards/oca/ocaBoard.jpeg";
        this.width=800;
        this.height=800;
    }
    
}
