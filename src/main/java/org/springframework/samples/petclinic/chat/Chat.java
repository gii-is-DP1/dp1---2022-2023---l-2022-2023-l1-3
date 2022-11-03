package org.springframework.samples.petclinic.chat;

import javax.persistence.Entity;

import org.springframework.samples.petclinic.mensaje.Mensaje;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Chat {
    
    // private Mensaje mensaje;
}
