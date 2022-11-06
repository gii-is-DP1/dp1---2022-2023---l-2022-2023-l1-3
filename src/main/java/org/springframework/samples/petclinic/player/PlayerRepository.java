package org.springframework.samples.petclinic.player;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends  CrudRepository<Player, Integer>{
    
    public List<Player> findAll();

    @Query("SELECT p FROM Player p where lower(p.username) = lower(:username)")
    public Player findByUsername(@Param("username") String username);

    Player save(Player p);
    
}
