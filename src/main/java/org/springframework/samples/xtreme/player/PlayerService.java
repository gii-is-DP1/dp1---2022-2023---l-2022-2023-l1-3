package org.springframework.samples.xtreme.player;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

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

    public List<Player> getAllPlayers(){
        return playerRepository.findAll();
    }

    public Player save(Player p){
        return this.playerRepository.save(p);       
    }

    @Transactional(readOnly = true)
    public Player findByUsername(String username){
        return this.playerRepository.findByUsername(username);
    }

}
