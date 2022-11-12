package org.springframework.samples.xtreme.cells;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.samples.xtreme.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class OcaCellType extends BaseEntity{
    
    @NotEmpty
    private String tipoCasilla; // enumerate



}
