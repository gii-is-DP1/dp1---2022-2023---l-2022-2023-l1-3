package org.springframework.samples.xtreme.pieces;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParchisPieceRepository extends CrudRepository<ParchisPiece, Integer> {

    @Query("SELECT p FROM ParchisPiece p WHERE p.game.id = ?1 AND p.player.id = ?2")
    public Collection<ParchisPiece> findPiecesByGameAndPlayer(Integer gameId, Integer pieceId);
    
}
