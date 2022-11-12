package org.springframework.samples.xtreme.pieces;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.samples.xtreme.board.OcaBoard;
import org.springframework.samples.xtreme.model.BaseEntity;
import org.springframework.samples.xtreme.player.Player;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="ocaPiece")
public class OcaPiece extends BaseEntity{

   public OcaPiece(){};

   @Column(name="postion")
   @Size(min=1, max=63)
   private Integer position;

   @ManyToOne(cascade= CascadeType.ALL)
   private OcaBoard board;

   @OneToOne
   private Player player;
    
}
