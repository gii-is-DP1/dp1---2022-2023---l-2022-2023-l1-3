package org.springframework.samples.xtreme.admin;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
import org.springframework.samples.xtreme.user.AuthoritiesService;
import org.springframework.samples.xtreme.user.User;
import org.springframework.samples.xtreme.user.UserService;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class AdminServiceTest {
    @Autowired
    protected AdminService adminService;
    @Autowired
    protected UserService userService;
    @Autowired
    protected AuthoritiesService authoritiesService;
    
    @Mock
    AdminRepository adminRepository;

    @Test
    public void getAllAdminsTest(){
        List<Admin> admins = adminService.getAllAdmins();
        assertNotNull(admins);
        assertThat(admins.size()>0).isTrue();
    }

    @Test
    void testSaveAdmin(){
        AdminService adminService = new AdminService(adminRepository, userService, authoritiesService);
        Admin admin = new Admin();
        User user = new User();
        user.setUsername("prueba");
        user.setPassword("abcd");
        admin.setEmail("prueba@prueba.com");
        admin.setUser(user);
        admin.setFirstName("prueba");
        admin.setLastName("prueba");

        try{
            adminService.save(admin);
            verify(adminRepository).save(admin);
        }catch(Exception e){
            fail("No se ha guardado correctamente");
        }
    }


    
}
