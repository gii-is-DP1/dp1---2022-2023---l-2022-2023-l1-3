package org.springframework.samples.petclinic.ficha;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.model.NamedEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "fichaType")
public class FichaType extends NamedEntity {
    
}
