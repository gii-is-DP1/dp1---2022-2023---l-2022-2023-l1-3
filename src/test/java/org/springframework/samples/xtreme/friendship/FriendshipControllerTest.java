package org.springframework.samples.xtreme.friendship;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.xtreme.configuration.SecurityConfiguration;
import org.springframework.samples.xtreme.game.GameService;
import org.springframework.samples.xtreme.invitation.InvitationService;
import org.springframework.samples.xtreme.player.Player;
import org.springframework.samples.xtreme.player.PlayerService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import lombok.With;

import java.util.ArrayList;
import java.util.Collection;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

@WebMvcTest(controllers = FriendshipController.class,
    excludeFilters = @ComponentScan.Filter(type=FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
    excludeAutoConfiguration = SecurityConfiguration.class)
public class FriendshipControllerTest {
    
    @Autowired
    MockMvc mockMvc;

    @MockBean
    private FriendshipService friendshipService;
    @MockBean
    private PlayerService playerService;

    private Friendship friendshipTest;


    @BeforeEach
    void setUp(){
        friendshipTest = new Friendship();
        Player player1 = playerService.findByUsername("elon");
        Player player2 = playerService.findByUsername("xavi");
        friendshipTest.setPlayer1(player1);
        friendshipTest.setPlayer2(player2);
        friendshipTest.setFriendshipState(FriendshipState.PENDING);
        Collection<Friendship> c = new ArrayList<>();
        c.add(friendshipTest);
        given(this.friendshipService.getFriendshipsByUsername("elon")).willReturn(c);
        given(this.friendshipService.getFriendshipsByUsername("xavi")).willReturn(c);


    }

    @WithMockUser
    @Test
    public void showFriendsTest() throws Exception{
        mockMvc.perform(get("/friends"))
            .andExpect(status().isOk())
            .andExpect(view().name("friendship/friends"))
            .andExpect(model().attributeExists("friendship", "myfriends", "myfriendsPending"));
    }

    @WithMockUser
    @Test
    public void showSendFriendshipsTest() throws Exception{
        mockMvc.perform(get("/friends/sendFriendship"))
            .andExpect(status().isOk())
            .andExpect(view().name("friendship/listNewFriends"))
            .andExpect(model().attributeExists("players"))
            .andExpect(model().attributeDoesNotExist("friends"));
    }

    @WithMockUser
    @Test
    public void showPendingFriendshipsByUsernameTest() throws Exception{
        Collection<Friendship> c = new ArrayList<>();
        c.add(friendshipTest);
        given(this.friendshipService.getFriendshipsByUsername("elon")).willReturn(c);
        
        mockMvc.perform(get("/friends/pendingFriendships/elon"))
            .andExpect(status().isOk())
            .andExpect(view().name("friendship/createFriendshipAccepted"));
            //.andExpect(model().attributeExists("player"));
    }

    @WithMockUser
    @Test
    public void createFriendshipTest() throws Exception{
        Collection<Friendship> c = new ArrayList<>();
        c.add(friendshipTest);
        given(this.friendshipService.getFriendshipsByUsername("xavi")).willReturn(c);
        
        mockMvc.perform(get("/friends/xavi"))
            .andExpect(status().isOk())
            .andExpect(view().name("friendship/createFriend"));
            //.andExpect(model().attributeExists("friendship","player"));
        
    }

    








}
