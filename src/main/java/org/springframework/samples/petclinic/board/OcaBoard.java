package org.springframework.samples.petclinic.board;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.pieces.OcaPiece;

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
