package org.springframework.samples.xtreme.game;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface GameRepository extends CrudRepository<Game, Integer> {

    public Collection<Game> findAll();

    @Query(value = "SELECT g FROM Game g WHERE g.isPublic = false")
    public Collection<Game> findByPrivateGame();
    
}
