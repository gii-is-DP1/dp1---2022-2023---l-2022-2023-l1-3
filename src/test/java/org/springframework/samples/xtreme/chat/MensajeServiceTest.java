package org.springframework.samples.xtreme.chat;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.xtreme.game.GameService;
import org.springframework.samples.xtreme.player.Player;
import org.springframework.samples.xtreme.player.PlayerService;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class MensajeServiceTest {

    @Autowired
	protected MensajeService mensajeService;
    @Autowired
	protected ChatService chatService;
    @Autowired
	protected PlayerService playerService;
    @Autowired
	protected GameService gameService;


    @Test
    void testSave(){
        Mensaje mensaje= new Mensaje();

        Chat chat= new Chat();
        //chat.setId(90);
        chat.setMensaje(new ArrayList<Mensaje>());
        chatService.save(chat);

        //mensaje.setId(90);
        mensaje.setChat(chat);
        mensaje.setPlayer(playerService.findByUsername("Hamil"));
        mensaje.setText("hola a todos");

        mensajeService.save(mensaje);
        assertTrue(mensajeService.findAll() != null) ;
    }
    
}
