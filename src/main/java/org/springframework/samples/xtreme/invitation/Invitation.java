package org.springframework.samples.xtreme.invitation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.samples.xtreme.game.Game;
import org.springframework.samples.xtreme.model.BaseEntity;
import org.springframework.samples.xtreme.player.Player;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "invitations")
public class Invitation extends BaseEntity{

    @OneToOne
    @JoinColumn(name = "player1", referencedColumnName = "id")
    @NotNull
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Player player1;

    @OneToOne
    @JoinColumn(name = "player2", referencedColumnName = "id")
    @NotNull
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Player player2;

    @OneToOne
    @JoinColumn(name = "game")
    @NotNull
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Game game;
    
    @Column(name="type")
    @Enumerated(EnumType.STRING)
    private InvitationType invitationType;

    
}
