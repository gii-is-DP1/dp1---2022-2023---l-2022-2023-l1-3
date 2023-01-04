package org.springframework.samples.xtreme.game;

import java.util.Collection;
import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.xtreme.chat.Chat;
import org.springframework.samples.xtreme.oca.OcaTurn;
import org.springframework.samples.xtreme.player.Player;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GameService {

    private GameRepository gameRepository;

    @Autowired
    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Transactional
    public Optional<Game> findGameById(Integer gameId) {
        return gameRepository.findById(gameId);
    }

    
    public Collection<Game> getAll(){
        return gameRepository.findAll();
    }

    @Transactional
    public void deleteGame(Integer gameId){
        gameRepository.deleteById(gameId);
    }
    
    @Transactional
    public void save(Game game){
        gameRepository.save(game);
    }

    @Transactional(readOnly = true)
    public Game findGameByCreatorPlayer(String username){
        return gameRepository.findGameByCreatorPlayer(username);
    }

    @Transactional(readOnly = true)
    public Chat findChatByGameId(Integer id){
        return gameRepository.findChatByGameId(id);
    }

    @Transactional(readOnly = true)
    public OcaTurn findOcaTurnByGameId(Integer id){
        return gameRepository.findOcaTurnByGameId(id);
    }

    
}
