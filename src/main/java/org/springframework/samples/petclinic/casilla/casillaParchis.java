package org.springframework.samples.petclinic.casilla;


import javax.persistence.Entity;

import org.springframework.samples.petclinic.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class casillaParchis extends Casilla{
  
    private String typeParchis;
    private Integer nextPosition;

}
