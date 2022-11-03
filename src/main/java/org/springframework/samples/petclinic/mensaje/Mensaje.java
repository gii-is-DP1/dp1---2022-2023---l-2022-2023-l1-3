package org.springframework.samples.petclinic.mensaje;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.samples.petclinic.chat.Chat;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Mensaje {

    @NotEmpty
    private String text;

    @ManyToOne
    @NotNull
    private Chat chat;
}
