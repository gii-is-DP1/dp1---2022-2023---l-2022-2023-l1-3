package org.springframework.samples.xtreme.player;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.xtreme.user.AuthoritiesService;
import org.springframework.samples.xtreme.user.UserRepository;
import org.springframework.samples.xtreme.user.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PlayerService {
    
    private PlayerRepository playerRepository;
    private UserService userService;
    private AuthoritiesService authoritiesService;
    @Autowired
    EntityManager em;

    @Autowired
    public PlayerService(PlayerRepository playerRepository, UserService userService, AuthoritiesService authoritiesService) {
        this.playerRepository = playerRepository;
        this.userService = userService;
        this.authoritiesService = authoritiesService;
    }
    @Transactional
    public List<Player> getAllPlayers(){
        return playerRepository.findAll();
    }
    @Transactional
    public void save(Player p){
        this.playerRepository.save(p); 
        this.userService.saveUser(p.getUser());
        this.authoritiesService.saveAuthorities(p.getUser().getUsername(), "player");
    }
    @Transactional
    public void update(Player p){
        this.playerRepository.save(p); 
        this.userService.updateUser(p.getUser());
        em.flush();
    }

    @Transactional(readOnly = true)
    public Player findByUsername(String username){
        return this.playerRepository.findByUsername(username);
    }

}
