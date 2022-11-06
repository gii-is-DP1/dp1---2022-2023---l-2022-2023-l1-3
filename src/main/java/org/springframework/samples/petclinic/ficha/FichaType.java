package org.springframework.samples.petclinic.ficha;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.springframework.samples.petclinic.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "fichaType")
public class FichaType extends BaseEntity {
 
    @NotEmpty
    private String color;
}
