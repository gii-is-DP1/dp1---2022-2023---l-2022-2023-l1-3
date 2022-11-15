package org.springframework.samples.xtreme.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.xtreme.user.AuthoritiesService;
import org.springframework.samples.xtreme.user.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdminService {

    private AdminRepository adminRepository;
    private UserService userService;
    private AuthoritiesService authoritiesService;

    @Autowired
    public AdminService(AdminRepository adminRepository, UserService userService, AuthoritiesService authoritiesService) {
        this.adminRepository = adminRepository;
        this.userService = userService;
        this.authoritiesService = authoritiesService;
    }

    public List<Admin> getAllAdmins(){
        return this.adminRepository.findAll();
    }
    
    public void save(Admin p){
        this.adminRepository.save(p);    
        this.userService.saveUser(p.getUser());  
        this.authoritiesService.saveAuthorities(p.getUser().getUsername(), "admin"); 
    }
    
    @Transactional(readOnly = true)
    public Admin findByUsername(String username){
        return this.adminRepository.findByUsername(username);
    }
}
