package org.springframework.samples.xtreme.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;



@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class UserServiceTest {
    
    @Mock
    UserRepository repo;
    
	@Autowired
	protected UserService userService;

    @Test
    void testSaveUser(){
        UserService userService = new UserService(repo);
        User u=new User();
        u.setUsername("userTest");
        u.setPassword("userTest");


        try {
            userService.saveUser(u);
            verify(repo).save(u);

        } catch (Exception e) {
            fail("No se ha guardado correctamente");
        }
    }
    

    @Test
	void testFindByUsername() {

		Optional<User> user = this.userService.findByUsername("xavi");
		assertThat(user.get().getPassword().equals("xavineta"));

        Boolean user2 = this.userService.findByUsername("hola").isPresent();
        assertFalse(user2);

	}
}
