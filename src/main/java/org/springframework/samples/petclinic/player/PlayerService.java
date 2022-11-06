package org.springframework.samples.petclinic.player;

import java.util.Collection;
import java.util.List;

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

    public List<Player> getAllPlayers(){
        return playerRepository.findAll();
    }

    public Player save(Player p){
        return this.playerRepository.save(p);       
    }

}
