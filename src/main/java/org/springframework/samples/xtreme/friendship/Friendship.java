package org.springframework.samples.xtreme.friendship;


import lombok.Getter;
import lombok.Setter;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.samples.xtreme.model.BaseEntity;
import org.springframework.samples.xtreme.player.Player;
import org.springframework.samples.xtreme.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@Table(name ="friendships") //para que no se puedan repetir las solicitudes
public class Friendship extends BaseEntity{
    
    @Column(name = "state")
    @Enumerated(EnumType.STRING)
    @NotNull
    private FriendshipState friendshipState = FriendshipState.PENDING;

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

    
}
