package org.springframework.samples.xtreme.turn;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParchisTurnRepository extends CrudRepository<ParchisTurn, Integer> {

    @Query("SELECT t FROM ParchisTurn t WHERE t.game.id = ?1")
    public ParchisTurn findByGame(String gameId);
    
}
