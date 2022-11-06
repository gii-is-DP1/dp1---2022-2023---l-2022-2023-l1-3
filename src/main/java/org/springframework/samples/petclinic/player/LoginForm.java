package org.springframework.samples.petclinic.player;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.samples.petclinic.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginForm {

    @NotNull
    @Size(min = 2, max = 30)
    @Column(name = "username")
    private String userName;

    @NotNull
    @Min(5)
    private String password;

}
