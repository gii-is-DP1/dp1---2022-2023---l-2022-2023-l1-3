package org.springframework.samples.petclinic.player;

import java.util.Collection;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PlayerService {
    
    private PlayerRepository playerRepository;

    @Autowired
    public PlayerService(PlayerRepository playerRepository) {

        this.playerRepository = playerRepository;
    }

    @Transactional(readOnly = true)
    public Collection<Player> getAll(){
        return playerRepository.findAll();
    }

}
