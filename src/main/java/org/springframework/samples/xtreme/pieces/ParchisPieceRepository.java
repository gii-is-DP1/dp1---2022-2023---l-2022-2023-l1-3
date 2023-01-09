package org.springframework.samples.xtreme.pieces;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.data.repository.CrudRepository;

public interface ParchisPieceRepository extends CrudRepository<ParchisPiece, Integer> {

    @Query("SELECT p FROM ParchisPiece p WHERE p.player.id = ?1 AND p.game.id = ?2")
    public Collection<ParchisPiece> findPieceByGameAndPlayer(Integer playerId, Integer gameId);
    
}
