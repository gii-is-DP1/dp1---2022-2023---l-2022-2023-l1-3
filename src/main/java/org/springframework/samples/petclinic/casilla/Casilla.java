package org.springframework.samples.petclinic.casilla;

import javax.persistence.Entity;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.samples.petclinic.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Casilla extends BaseEntity{
    
    @NotNull
    @Min(0)
    private Integer posicion;

}
