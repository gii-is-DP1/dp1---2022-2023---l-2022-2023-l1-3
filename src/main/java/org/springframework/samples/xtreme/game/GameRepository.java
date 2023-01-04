package org.springframework.samples.xtreme.game;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.xtreme.chat.Chat;
import org.springframework.samples.xtreme.player.Player;

public interface GameRepository extends CrudRepository<Game, Integer> {

    public Collection<Game> findAll();

    @Query(value = "SELECT g FROM Game g WHERE g.isPublic = false")
    public Collection<Game> findByPrivateGame();

    @Query(value = "SELECT g FROM Game g WHERE g.creatorPlayer.user.username = ?1 AND g.stateGame = 'WAITING_PLAYERS'")
    public Game findGameByCreatorPlayer(String username);

    @Query(value = "SELECT g.chat FROM Game g WHERE g.id = ?1")
    public Chat findChatByGameId(Integer id);





    
    
}
