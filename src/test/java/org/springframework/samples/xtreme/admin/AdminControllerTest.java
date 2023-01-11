package org.springframework.samples.xtreme.admin;

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
import org.springframework.samples.xtreme.user.User;
import org.springframework.samples.xtreme.user.UserService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

import org.apache.taglibs.standard.lang.jstl.ModulusOperator;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

@WebMvcTest(controllers = AdminController.class,
    excludeFilters = @ComponentScan.Filter(type=FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
    excludeAutoConfiguration = SecurityConfiguration.class)
public class AdminControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private AdminService adminService;
    @MockBean
    private GameService gameService;
    @MockBean
    private AdminValidator adminValidator;
    

    private Admin adminTest;

    @BeforeEach
    void setUp(){
        adminTest = new Admin();
        User user = new User();
        adminTest.setFirstName("andres");
        adminTest.setLastName("iniesta");
        adminTest.setUser(user);
        adminTest.setEmail("user2@xtreme.com");
        given(this.adminService.findByUsername("xavi")).willReturn(adminTest);

    }

    @WithMockUser
    @Test
    public void showAdminListTest() throws Exception{
        mockMvc.perform(get("/admins"))
            .andExpect(status().isOk())
            .andExpect(view().name("admins/adminList"))
            .andExpect(model().attributeExists("admins"));
    }

    @WithMockUser
    @Test
    public void showAllGamesTest() throws Exception{
        mockMvc.perform(get("/admins/listAllGames"))
            .andExpect(status().isOk())
            .andExpect(view().name("admins/listAllGames"))
            .andExpect(model().attributeExists("games"));
    }

    
    @WithMockUser
    @Test
    public void createAdminTest() throws Exception {
        mockMvc.perform(post("/admins/create")
        .with(csrf())
        .param("username", "user2")
        .param("email", "user2@xtreme.com"))
        .andExpect(status().isOk());
        
    }


    
}
