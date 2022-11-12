package org.springframework.samples.xtreme.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdminService {

    private AdminRepository adminRepository;

    @Autowired
    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public List<Admin> getAllAdmins(){
        return this.adminRepository.findAll();
    }
    
    public Admin save(Admin p){
        return this.adminRepository.save(p);       
    }

    @Transactional(readOnly = true)
    public Admin findByUsername(String username){
        return this.adminRepository.findByUsername(username);
    }
}
