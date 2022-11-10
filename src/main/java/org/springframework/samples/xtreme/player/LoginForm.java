package org.springframework.samples.xtreme.player;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.samples.xtreme.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginForm {

    @NotNull
    @Column(name = "username")
    private String username;

    @NotNull
    private String password;

}
