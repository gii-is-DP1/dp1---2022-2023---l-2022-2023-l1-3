package org.springframework.samples.petclinic.player;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {
    
    private PlayerRepository playerRepository;

    @Autowired
    public PlayerService(PlayerRepository playerRepository) {

        this.playerRepository = playerRepository;
    }

    @Transactional
    public Collection<Player> getAll(){
        return playerRepository.findAll();
    }

}
