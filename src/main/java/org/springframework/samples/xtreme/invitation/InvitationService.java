package org.springframework.samples.xtreme.invitation;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InvitationService {

    private InvitationRepository invitationRepository;

    @Autowired
    public InvitationService(InvitationRepository invitationRepository){
        this.invitationRepository = invitationRepository;
    }

    @Transactional(readOnly = true)
    public Collection<Invitation> getAll(){
        return invitationRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Collection<Invitation> findInvitationByUsername(String username){
        return invitationRepository.findAllByUsername(username);
    }

    @Transactional(readOnly = true)
    public Collection<Invitation> findRecievedInvitationsByUsername(String username){
        return invitationRepository.findRecievedInvitationsByUsername(username);
    }


    @Transactional
    public void save(Invitation i){
        invitationRepository.save(i);
    }


    
}
