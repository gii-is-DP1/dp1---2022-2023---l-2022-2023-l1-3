package org.springframework.samples.petclinic.player;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.samples.petclinic.game.Game;
import org.springframework.samples.petclinic.model.Person;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "player")
public class Player extends Person {

    public Player(){}

    @Column(name = "username", unique = true)
    @NotEmpty
    @Size(min = 4, max = 10)
    private String username;

    @Column(name = "password")
    @NotEmpty
    private String password;

    @Column(name = "email", unique = true)
    @Email
    @NotEmpty
    private String email;


    @ManyToMany
    @JoinColumn(name = "games")
    private List<Game> games;

    //@Column(name = "enabled")
    //private Boolean enabled = false;

}
