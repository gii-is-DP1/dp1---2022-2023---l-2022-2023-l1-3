package org.springframework.samples.xtreme.oca;

import org.springframework.samples.xtreme.game.Game;
import org.springframework.samples.xtreme.model.BaseEntity;
import org.springframework.samples.xtreme.player.Player;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="ocaPiece")
public class OcaPiece extends BaseEntity{


   @Column(name="position")
   @DecimalMax(value="63", inclusive=true)
	@DecimalMin(value="1", inclusive=true)
   private Integer position;

   @OneToOne
   private Player player;

   @ManyToOne
   private Game game;

   private Integer penalization = 0;
	
	public OcaPiece() {
		this.position=1;
	}
    
}
