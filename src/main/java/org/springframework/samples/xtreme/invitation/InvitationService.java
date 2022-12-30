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

    @Transactional(readOnly = true)
    public Invitation findInvitationPlayerToGame(Integer playerId,Integer gameId){
        return invitationRepository.findInvitationPlayerToGame(playerId,gameId);
    }

    @Transactional(readOnly = true)
    public Invitation findInvitationToGameByPlayers(Integer player1Id,Integer player2Id,Integer gameId){
        return invitationRepository.findInvitationToGameByPlayers(player1Id,player2Id,gameId);
    }


    @Transactional
    public void save(Invitation i){
        invitationRepository.save(i);
    }


    
}
