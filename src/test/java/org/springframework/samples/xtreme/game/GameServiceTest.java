package org.springframework.samples.xtreme.game;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.verify;

import java.util.*;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class GameServiceTest {
    
    @Autowired
    protected GameService gameService;

    @Mock
    GameRepository gameRepository;

    @Test
    public void testGetAll(){
        Collection<Game> games = gameService.getAll();
        assertThat(games.size()>0).isTrue();

    }

    @Test
    public void testFindGameById(){
        Optional<Game> game = gameService.findGameById(1);
        assertNotNull(game);

        Optional<Game> game2 = gameService.findGameById(10);
        assertEquals(Optional.empty(), game2);;
    }

    @Test
    public void testSaveGame(){
        GameService gameService = new GameService(gameRepository);
        Game game = new Game();
        game.setGameName("Partida 2");
        game.setTypeGame(GameType.PARCHIS);
        game.setNumPlayers(4);
        game.setIsPublic(true);

        try{
            gameService.save(game);
            verify(gameRepository).save(game);
        }catch(Exception e){
            fail("El juego no ha sido creado correctamente");
        }
        
    }

    @Test
    public void testDeleteGame(){
        Game game = new Game();
        game.setGameName("Partida 3");
        game.setTypeGame(GameType.PARCHIS);
        game.setNumPlayers(4);
        game.setIsPublic(true);

        try{
            gameService.save(game);
            gameService.deleteGame(game.getId());
            assertEquals(Optional.empty(), gameService.findGameById(game.getId()));
        }catch(Exception e){
            fail("El juego no fue eliminado correctamente");
        }
       
    }
    
}
