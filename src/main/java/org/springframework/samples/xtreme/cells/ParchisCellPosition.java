package org.springframework.samples.xtreme.cells;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "parchis_cell_position")
public class ParchisCellPosition {
    @Id
	String position_id;

    @Column(name = "position_x")
    private Double position_x;

    @Column(name = "position_y")
    private Double position_y;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name ="position")
    private ParchisCell cell;

}
