package org.springframework.samples.xtreme.player;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends  CrudRepository<Player, Integer>{
    
    public List<Player> findAll();

    //@Query("SELECT p FROM Player p in (player.username) as user WHERE user.username = ?1")
    //@Query("SELECT p FROM Players p JOIN p.username u WHERE u.username = ?1")
    //@Query("SELECT p FROM Players p JOIN Users u ON p.username = u.username WHERE u.username = ?1")
    //@Query("SELECT p FROM Players p, IN (player.user) AS user WHERE user.usename = ?1")
    //@Query("SELECT p FROM Players p INNER JOIN (SELECT u FROM Users WHERE u.username = ?1) u ON u.username = p.username")
    @Query("SELECT p FROM Player p WHERE p.user.username = ?1")
    public Player findByUsername(String username);

    
}
