package org.springframework.samples.xtreme.chat;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MensajeService {
    private MensajeRepository mensajeRepository;

    @Autowired
    public MensajeService(MensajeRepository mensajeRepository) {
        this.mensajeRepository = mensajeRepository;
    }
    @Transactional(readOnly = true)
    public Iterable<Mensaje> findAll(){
       return mensajeRepository.findAll();
    }

    @Transactional
    public Mensaje save(Mensaje m){
       return mensajeRepository.save(m);
    }

}
