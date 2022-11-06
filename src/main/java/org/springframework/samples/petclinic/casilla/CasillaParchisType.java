package org.springframework.samples.petclinic.casilla;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;

import org.springframework.samples.petclinic.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CasillaParchisType extends BaseEntity{
    
    @NotEmpty
    private String tipoCasilla; // enumerate

}
