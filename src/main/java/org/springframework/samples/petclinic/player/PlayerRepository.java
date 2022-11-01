package org.springframework.samples.petclinic.player;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends  CrudRepository<Player, Integer>{
    
    public Collection<Player> findAll();

    @Query("SELECT p FROM Player p where lower(p.username) = lower(:username)")
    public Player findByUsername(@Param("username") String username);
    
}
