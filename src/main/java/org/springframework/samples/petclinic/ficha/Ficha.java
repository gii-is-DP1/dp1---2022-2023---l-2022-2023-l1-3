package org.springframework.samples.petclinic.ficha;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.samples.petclinic.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "ficha")
public class Ficha extends BaseEntity {

    @Column(name = "position")
    @NotEmpty
    private Integer position;

    @ManyToOne
	@JoinColumn(name = "fichaType_id")
    @NotNull
    private FichaType fichaType;
}
