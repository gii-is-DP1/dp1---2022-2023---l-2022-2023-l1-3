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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "username", referencedColumnName = "username")
	private User user;

    @ManyToMany
    @JoinColumn(name = "games")
    private List<Game> games;
    

    //@Column(name = "enabled")
    //private Boolean enabled = false;

}
