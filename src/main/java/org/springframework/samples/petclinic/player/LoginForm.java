package org.springframework.samples.petclinic.player;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginForm {

    @NotNull
    @Size(min = 2, max = 30)
    private String userName;

    @NotNull
    @Min(5)
    private String password;

}
