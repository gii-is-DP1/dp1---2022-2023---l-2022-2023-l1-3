package org.springframework.samples.petclinic.tablero;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.samples.petclinic.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tablero")
public class Tablero extends BaseEntity {

    @Column(name = "numStall")
    @NotEmpty
    private Integer numStall;

    /* 
    @OneToMany
    @JoinColumn(name = "casilla_id")
    @NotNull
    private Casilla casilla;
    */
}
