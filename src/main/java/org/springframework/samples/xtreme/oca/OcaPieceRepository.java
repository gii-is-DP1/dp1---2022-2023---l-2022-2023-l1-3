package org.springframework.samples.xtreme.oca;
import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.xtreme.player.Player;

public interface OcaPieceRepository extends CrudRepository<OcaPiece, Integer>{
	
	@Query("SELECT DISTINCT piece FROM OcaPiece piece, IN (piece.player) AS player WHERE player.id LIKE :playerId")
	public Collection<OcaPiece> findByPlayerId(@Param("playerId") int playerId);

	@Query(value = "SELECT p FROM OcaPiece p WHERE p.player.id = ?1 AND p.game.id = ?2")
    public OcaPiece findPiecebyGameAndPlayer(Integer idPlayer,Integer gameId);
}
