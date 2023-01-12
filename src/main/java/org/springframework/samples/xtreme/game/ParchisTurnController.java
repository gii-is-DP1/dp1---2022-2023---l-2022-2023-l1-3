package org.springframework.samples.xtreme.game;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.xtreme.board.ParchisBoard;
import org.springframework.samples.xtreme.board.ParchisBoardService;
import org.springframework.samples.xtreme.cells.ParchisCell;
import org.springframework.samples.xtreme.cells.ParchisCellService;
import org.springframework.samples.xtreme.pieces.Color;
import org.springframework.samples.xtreme.pieces.ParchisPiece;
import org.springframework.samples.xtreme.pieces.ParchisPieceService;
import org.springframework.samples.xtreme.player.Player;
import org.springframework.samples.xtreme.player.PlayerService;
import org.springframework.samples.xtreme.util.UserUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(path="/parchis")
public class ParchisTurnController {

    private static final String PARCHIS_GAME="games/parchisGame";
    private static final String PARCHIS_TURN="games/parchisTurn";
    

    private final GameService gameService;
    private final PlayerService playerService;
    private final ParchisPieceService parchisPieceService;
    private final ParchisCellService parchisCellService;
    private final ParchisBoardService parchisBoardService;

    private UserUtils userUtils = new UserUtils();


    @Autowired
    public ParchisTurnController(GameService gameService, PlayerService playerService,ParchisPieceService parchisPieceService,
            ParchisCellService parchisCellService,ParchisBoardService parchisBoardService){
        this.gameService = gameService;
        this.playerService = playerService;
        this.parchisPieceService=parchisPieceService;
        this.parchisCellService=parchisCellService;
        this.parchisBoardService=parchisBoardService;
    }


    @GetMapping(path="/{gameId}/{playerId}")
	public  ModelAndView playTurnParchis (@PathVariable("gameId") int gameId,@PathVariable("playerId") Integer playerId){
		Player player=this.playerService.findById(playerId);
        Game game= this.gameService.findGameById(gameId).get();
        
        if(game.getPlayers().get(game.getI()) != player){
            return  new ModelAndView("redirect:/games/lobby/"+gameId);
        }

        ModelAndView mav = new ModelAndView(PARCHIS_TURN);
        game.throwDice();
        this.gameService.save(game);
		/*ParchisTurn turn = this.parchisTurnService.findTurnById(parchisGameId);
		if(!turn.getIsThrowed()){
			turn.throwDices();
			turn.setRepeatTurn(turn.getDice1()==turn.getDice2());
		}*/
		
		List<ParchisPiece> pieces = player.getParchisPieces(); 

        for(int i = 0; i <= pieces.size()-1; i++) {
			mav.addObject("piece"+i,pieces.get(i));
        }
        mav.addObject("player",player);
        mav.addObject("game",this.gameService.findGameById(gameId).get());
        mav.addObject("pieces",pieces);
		return mav;		
	}
}
