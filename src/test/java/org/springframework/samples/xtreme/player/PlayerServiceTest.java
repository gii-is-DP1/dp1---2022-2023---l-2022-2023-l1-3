package org.springframework.samples.xtreme.player;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collection;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.xtreme.user.AuthoritiesService;
import org.springframework.samples.xtreme.user.User;
import org.springframework.samples.xtreme.user.UserService;
import org.springframework.stereotype.Service;


@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class PlayerServiceTest {
    @Mock
    PlayerRepository repo;
    
	@Autowired
	protected PlayerService playerService;
    @Autowired
    protected UserService userService;
    @Autowired
    protected AuthoritiesService authoritiesService;

    @Test
    void testGetAll(){
        Collection<Player> players = this.playerService.getAllPlayers();
        assertThat(players.size()>0).isTrue();
    }

    @Test
    void testSaveUser(){
        PlayerService playerService = new PlayerService(repo, userService, authoritiesService);
        User user = new User();
        user.setUsername("prueba");
        user.setPassword("abcd");
        Player p=new Player();
        p.setUser(user);
        p.setFirstName("playerTest");
        p.setEmail("playerTest@test.com");
        p.setLastName("playerTest");
        
    
            playerService.save(p);
            verify(repo).save(p);

        
    }
    

   @Test
	void testFindByUsername() {

		Player p = this.playerService.findByUsername("xavi");
		assertThat(p.getEmail().equals("xavineta"));

        Player p2 = this.playerService.findByUsername("hola");
        assertNull(p2);

	}

}
