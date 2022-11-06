package org.springframework.samples.petclinic.administrator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdministratorService {

    @Autowired
    private AdministratorRepository ar;

    public Administrator save(Administrator p){
        return this.ar.save(p);       
    }
}
