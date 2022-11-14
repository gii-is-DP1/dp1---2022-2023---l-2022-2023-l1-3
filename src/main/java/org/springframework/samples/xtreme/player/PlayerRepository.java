package org.springframework.samples.xtreme.player;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends CrudRepository<Player, Integer>{
    
    public List<Player> findAll();

    @Query("SELECT p FROM Player p WHERE p.user.username = ?1")
    public Player findByUsername(String username);

    
}
