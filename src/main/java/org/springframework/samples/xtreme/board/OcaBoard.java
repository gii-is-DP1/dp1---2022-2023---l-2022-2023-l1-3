package org.springframework.samples.xtreme.board;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.samples.xtreme.model.BaseEntity;
import org.springframework.samples.xtreme.pieces.OcaPiece;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="ocaBoard")
public class OcaBoard extends BaseEntity{

    @OneToMany
    private List<OcaPiece> pieces;
    
}
