package org.springframework.samples.xtreme.admin;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class AdminRepositoryTest {

    @Autowired
    AdminRepository adminRepository;

    @Test
    public void testFindAll(){
        List<Admin> admins = adminRepository.findAll();
        assertNotNull(admins, "El repositorio ha devuelto una lista nula");
        assertThat(admins.size()>0).isTrue();

    }

    @Test
    public void testFindByUsernameSuccesful() {
        Admin admin = adminRepository.findByUsername("xtreme");
        assertNotNull(admin,"El usuario fue encontrado correctamente");
        
    }

    @Test
    public void testFindByUsernameUnsuccesful() {
        Admin admin = adminRepository.findByUsername("extreme");
        assertNull(admin, "El usuario no fue encontrado");
        
    }
    
}
