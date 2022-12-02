package org.springframework.samples.xtreme.player;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.samples.xtreme.game.Game;
import org.springframework.samples.xtreme.model.Person;
import org.springframework.samples.xtreme.user.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "players")
public class Player extends Person {

    @Column(name = "email", unique = true)
    @Email
    @NotEmpty
    private String email;


    @Column(name = "is_online")
    private Boolean isOnline= false;

    @Column(name = "pic_profile")
    private String picProfile="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTsDo9LQ-Zw5EFDG-GpCOGYGB5F5k6RMmEYAw&usqp=CAU";


    @Valid
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "username", referencedColumnName = "username")
	private User user;

    @ManyToMany
    @JoinColumn(name = "games")
    private List<Game> games;

}
