package org.springframework.samples.petclinic.casilla;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.tablero.Tablero;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CasillaOca extends BaseEntity{
    
    @NotNull
    @Value("0")
    @Min(0)
    private Integer nextPosition;
    
    @ManyToOne
    @NotNull
    @JoinColumn(name="casilla_oca_type_id")
    private CasillaOcaType casillaOcaType;

    @ManyToOne
    @JoinColumn(name = "tablero_id")
    @NotNull
    private Tablero tablero; 

    
}
