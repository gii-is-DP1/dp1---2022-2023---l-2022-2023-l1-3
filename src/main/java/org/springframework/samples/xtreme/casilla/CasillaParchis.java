package org.springframework.samples.xtreme.casilla;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.samples.xtreme.model.BaseEntity;
import org.springframework.samples.xtreme.tablero.Tablero;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CasillaParchis extends BaseEntity{

/*
    @NotNull
    @Value("0")
    private Integer nextPosition;
*/
@ManyToOne
@NotNull
@JoinColumn(name="casilla_parchis_type_id")
private CasillaParchisType casillaParchisType;


@ManyToOne
@JoinColumn(name = "tablero_id")
@NotNull
private Tablero tablero;


}
