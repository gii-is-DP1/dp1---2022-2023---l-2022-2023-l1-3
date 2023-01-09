package org.springframework.samples.xtreme.invitation;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.verify;

import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.xtreme.game.Game;
import org.springframework.samples.xtreme.game.GameService;
import org.springframework.samples.xtreme.player.Player;
import org.springframework.samples.xtreme.player.PlayerService;
import org.springframework.stereotype.Service;



@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class InvitationServiceTest {

    @Autowired
    protected InvitationService invitationService;

    @Autowired
    protected PlayerService playerService;

    @Autowired
    protected GameService gameService;

    @Mock
    InvitationRepository invitationRepository;

    @Test
    void testGetAll(){
        Collection<Invitation> inv = this.invitationService.getAll();
        assertThat(inv.size()>0).isTrue();

    }

    @Test
    void testFindInvitationByUsername(){
        Collection<Invitation> inv = this.invitationService.findInvitationByUsername("Hamil");
        assertThat(inv.size()>0).isTrue();

        Boolean b = inv.stream().allMatch(
            x->x.getPlayer1().getUser().getUsername().equals("Hamil") || 
            x.getPlayer2().getUser().getUsername().equals("Hamil")
        );
        assertTrue(b);

        Collection<Invitation> inv2 = this.invitationService.findInvitationByUsername("hola");
        assertThat(inv2).isEmpty();;

        Boolean b2 = inv.stream().allMatch(
            x->x.getPlayer1().getUser().getUsername().equals("hola") || 
            x.getPlayer2().getUser().getUsername().equals("hola")
        );
        assertFalse(b2);

    }

    @Test
    void testFindRecievedInvitationsByUsername(){
        Collection<Invitation> r = invitationService.findRecievedInvitationsByUsername("user5");
        assertThat(r.size()>0).isTrue();

        Boolean b = r.stream().anyMatch(x -> x.getPlayer2().getUser().getUsername().equals("user5"));
        assertTrue(b);

        Boolean b2 = r.stream().anyMatch(x -> x.getPlayer2().getUser().getUsername().equals("Hamil"));
        assertFalse(b2);

        Collection<Invitation> r1 = invitationService.findRecievedInvitationsByUsername("Hamil");
        assertThat(r1).isEmpty();
    }

    @Test
    void testSaveInvitation(){
        InvitationService invitationService = new InvitationService(invitationRepository);
        Player player1 = playerService.findByUsername("elon");
        Player player2 = playerService.findByUsername("user1");
        Game game = gameService.findGameById(2).get();

        Invitation inv = new Invitation();
        inv.setPlayer1(player1);
        inv.setPlayer2(player2);
        inv.setGame(game);
        inv.setInvitationType(InvitationType.PLAYER);
        

        try{
            invitationService.save(inv);
            verify(invitationRepository).save(inv);
        }catch(Exception e){
            fail("No se ha guardado correctamente");
        }

    }




    
}
