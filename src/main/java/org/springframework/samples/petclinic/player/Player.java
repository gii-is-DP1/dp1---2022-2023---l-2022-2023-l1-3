package org.springframework.samples.petclinic.player;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

//import org.springframework.samples.petclinic.game.GamePlayerRecord;
import org.springframework.samples.petclinic.model.BaseEntity;
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
    private String username = "No name";

    @Column(name = "password")
    @NotEmpty
    private String password = "No pass";

    @Column(name = "email")
    @Email
    @NotEmpty
    private String email = "";

    @Column(name = "profile_pic")
    @NotEmpty
    private String profilePic = "";

    //@Column(name = "enabled")
    //private Boolean enabled = false;

    //@OneToMany(mappedBy = "game")
    //Set<GamePlayerRecord> games;

}
