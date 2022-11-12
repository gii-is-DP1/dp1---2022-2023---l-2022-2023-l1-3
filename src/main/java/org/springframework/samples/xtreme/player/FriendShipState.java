package org.springframework.samples.xtreme.player;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

import org.springframework.samples.xtreme.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class FriendShipState extends BaseEntity{

@NotEmpty
private String fsState; // PENDING , ACCEPTED , REJECTED
/* 
@ManyToOne
@JoinColumn(name = "player_id")
private Player player;
    */
}
