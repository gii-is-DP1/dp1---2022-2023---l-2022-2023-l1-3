package org.springframework.samples.xtreme.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.xtreme.configuration.SecurityConfiguration;
import org.springframework.samples.xtreme.player.PlayerService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;



@WebMvcTest(controllers=UserController.class,
    excludeFilters = @ComponentScan.Filter(type=FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
    excludeAutoConfiguration = SecurityConfiguration.class) 
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PlayerService playerService;

    @WithMockUser
    @Test
    public void testLogout() throws Exception {
        mockMvc.perform(get("/users/logout-screen"))
        .andExpect(status().isOk())
        .andExpect(view().name("users/logout"));

    }

    @WithMockUser
    @Test
    public void testLogin() throws Exception {
        mockMvc.perform(get("/users/login"))
        .andExpect(status().isOk())
        .andExpect(view().name("users/loginForm"))
        .andExpect(model().attributeExists("user"))
        .andExpect(model().attributeHasNoErrors("user"));
    }

    @WithMockUser
    @Test
    public void testHome() throws Exception {
        mockMvc.perform(get("/home"))
        .andExpect(status().isOk())
        //.andExpect(view().name("users/home"))
        .andExpect(model().attributeDoesNotExist("users"))
        .andExpect(model().attributeDoesNotExist("esHost"));
    }
}
