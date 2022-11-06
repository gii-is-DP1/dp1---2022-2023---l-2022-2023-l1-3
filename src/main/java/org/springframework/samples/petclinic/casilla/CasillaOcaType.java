package org.springframework.samples.petclinic.casilla;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.samples.petclinic.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CasillaOcaType extends BaseEntity{
    
    @NotEmpty
    private String tipoCasilla; // enumerate



}
