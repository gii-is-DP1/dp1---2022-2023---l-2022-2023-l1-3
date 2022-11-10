package org.springframework.samples.xtreme.tablero;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.samples.xtreme.casilla.CasillaOca;
import org.springframework.samples.xtreme.casilla.CasillaParchis;
import org.springframework.samples.xtreme.ficha.Ficha;
import org.springframework.samples.xtreme.model.BaseEntity;

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



}