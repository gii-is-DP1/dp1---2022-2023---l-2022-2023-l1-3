package org.springframework.samples.petclinic.admin;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.samples.petclinic.game.Game;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="admin")
public class Admin {
    
    @NotEmpty
    @Column(name="first_name")
    private String fisrtName="";
    
    @NotEmpty
    @Column(name="last_name")
    private String lastName="";

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


    @ManyToMany
    @JoinColumn(name="games")
    Collection<Game> games;

}
