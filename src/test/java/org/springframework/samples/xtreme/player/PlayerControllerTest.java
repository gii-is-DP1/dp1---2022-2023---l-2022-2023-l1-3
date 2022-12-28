package org.springframework.samples.xtreme.player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.xtreme.configuration.SecurityConfiguration;
import org.springframework.samples.xtreme.game.GameService;
import org.springframework.samples.xtreme.player.PlayerService;
import org.springframework.samples.xtreme.user.Authorities;
import org.springframework.samples.xtreme.user.User;
import org.springframework.samples.xtreme.user.UserService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.BDDMockito.given;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.HashSet;
import java.util.Set;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;



@WebMvcTest(controllers=PlayerController.class,
    excludeFilters = @ComponentScan.Filter(type=FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
    excludeAutoConfiguration = SecurityConfiguration.class) 
public class PlayerControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PlayerService playerService;
    @MockBean
    private UserService userService;
    @MockBean
    private GameService gameService;
    @MockBean
    private PlayerValidator playerValidator;

	private Player playerTest;

	@BeforeEach
	void setup() {

		playerTest = new Player();
		playerTest.setId(777);
		playerTest.setFirstName("Rodolfo");
		playerTest.setLastName("elTravieso");
        playerTest.setEmail("aupaAtleti@gmail.com");
        playerTest.setIsOnline(false);
		User user=new User();
        user.setUsername("elRodolf777");
        user.setPassword("siuuuu");
        user.setEnabled(true);
        Set<Authorities> authorities=new HashSet<Authorities>();
        Authorities a=new Authorities();
        a.setId(777);
        a.setUser(user);
        a.setAuthority("player");
        authorities.add(a);
        user.setAuthorities(authorities);
        playerTest.setUser(user);        
		//playerTest.setPoints(1);
		given(this.playerService.findByUsername("elRodolf777")).willReturn(playerTest);

	}


    @WithMockUser
    @Test
    public void showPlayerListTest() throws Exception {
        mockMvc.perform(get("/players"))
        .andExpect(status().isOk())
        .andExpect(view().name("players/playersList"))
        .andExpect(model().attributeExists("players"));
    }

    @WithMockUser
    @Test
    public void viewFormTest() throws Exception {
        mockMvc.perform(get("/players/create"))
        .andExpect(status().isOk())
        //.andExpect(view().name("players/createPlayerForm"))
        .andExpect(model().attributeDoesNotExist("players"));
    }

    @WithMockUser
    @Test
    public void showProfileTest() throws Exception {
        given(this.playerService.findByUsername("elRodolf777")).willReturn(playerTest);

        mockMvc.perform(get("/players/elRodolf777"))
        .andExpect(status().isOk())
       // .andExpect(view().name("players/viewProfile"))
        .andExpect(model().attributeDoesNotExist("players"))
        .andExpect(model().attributeDoesNotExist("isBaned"))
        .andExpect(model().attributeDoesNotExist("isAdmin"))
        .andExpect(model().attributeDoesNotExist("isUserEqual"));

    }

    @WithMockUser
    @Test
    public void editProfileTest() throws Exception {
        given(this.playerService.findByUsername("elRodolf777")).willReturn(playerTest);

        mockMvc.perform(get("/players/elRodolf777/edit"))
        .andExpect(status().isOk())
       // .andExpect(view().name("players/editProfile"))
        .andExpect(model().attributeDoesNotExist("players"))
        .andExpect(model().attributeDoesNotExist("isUserEqual"));

    }
}