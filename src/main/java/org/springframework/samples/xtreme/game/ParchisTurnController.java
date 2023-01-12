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

    private Integer extra=null;

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
		if(extra!=null){
            game.setDice(extra);
            extra=null;
        }
        this.gameService.save(game);
		List<ParchisPiece> pieces = player.getParchisPieces(); 

        for(int i = 0; i <= pieces.size()-1; i++) {
			mav.addObject("piece"+i,pieces.get(i));
        }
        mav.addObject("player",player);
        mav.addObject("game",this.gameService.findGameById(gameId).get());
        mav.addObject("pieces",pieces);
		return mav;		
	}

    @GetMapping(path="/move/{gameId}/{playerId}/{pieceId}")
	public  ModelAndView movePiece (@PathVariable("gameId") int gameId,@PathVariable("playerId") Integer playerId,@PathVariable("pieceId") Integer pieceId){
        Game game = this.gameService.findGameById(gameId).get();
        Integer dice = game.getDice();
        Player currentPlayer = this.playerService.findById(playerId);
        ParchisPiece p = this.parchisPieceService.findById(pieceId);

        if(this.parchisCellService.findByPosition(p.getPosicion()).isHouse()) {
            
            if((p.getColor().equals(Color.BLUE) && this.parchisCellService.findByPosition(56).isBloqueo()) ||
            (p.getColor().equals(Color.YELLOW) && this.parchisCellService.findByPosition(5).isBloqueo()) ||
            (p.getColor().equals(Color.RED) && this.parchisCellService.findByPosition(39).isBloqueo()) ||
            (p.getColor().equals(Color.GREEN) && this.parchisCellService.findByPosition(22).isBloqueo()) ||
            game.getDice() != 5) {
                if(game.getDice() == 6){
                    return new ModelAndView("redirect:/parchis/"+gameId+"/"+playerId);		
                } 
                p.setCanPlay(false);
                game.nextTurn();
                this.gameService.save(game);
                return new ModelAndView("redirect:/games/lobby/"+gameId);


            } else{ //  if(game.getDice() == 5)
                sacarFichaDeCasa(p);
                game.nextTurn();
                this.gameService.save(game);
                return new ModelAndView("redirect:/games/lobby/"+gameId);

            }
        } else{
                Integer position=p.getPosicion();
                Integer nextPos=position+dice;
                if(this.parchisCellService.findByPosition(nextPos).isFinalCell() && this.parchisCellService.findByPosition(nextPos).getPieces().size()==4 ){
                    game.setPlayerWinner(currentPlayer);
                    game.setStateGame(GameState.FINISHED);
                    this.gameService.save(game);
                    return new ModelAndView("redirect:/games/lobby/"+gameId);
                }
                Boolean bloqueo=false;
                for(int i =position+1; i<=nextPos; i++) {
                    ParchisCell cell=this.parchisCellService.findByPosition(i);
                    if(cell.isBloqueo()) {
                        p.setCanPlay(false);
                        this.parchisPieceService.save(p);
                        bloqueo= true;		
                    }
                 } if(!bloqueo){
                        eat(nextPos, p);
                        if(extra!=null){
                            return new ModelAndView("redirect:/parchis/move/"+gameId+"/"+playerId+"/"+pieceId);		
                        }
                        avanzar(nextPos, p);
                        //
                    }
                    if(game.getDice() == 6){
                        return new ModelAndView("redirect:/parchis/"+gameId+"/"+playerId);		
                    } 
                game.nextTurn();
                this.gameService.save(game);
                return new ModelAndView("redirect:/games/lobby/"+gameId);			
        }
    }
    public void eat(Integer nextPos, ParchisPiece parchisPiece){
        ParchisCell cell = this.parchisCellService.findByPosition(nextPos);
        if(cell.getPieces().size()==1 && cell.getPieces().get(0).getColor() != parchisPiece.getColor()){
            ParchisPiece piece2=this.parchisPieceService.findById(cell.getPieces().get(0).getId());
            piece2.setCell(this.parchisCellService.findByPosition(piece2.getBaseCell()));
            this.parchisPieceService.save(piece2);
            cell.quitarFicha(piece2);
            ParchisCell house= this.parchisCellService.findByPosition(piece2.getBaseCell());
            house.colocarFicha(piece2);
            this.parchisCellService.save(cell);
            this.parchisCellService.save(house);
            extra=20;
        }
    }
    public void avanzar(Integer nextPos, ParchisPiece parchisPiece){
        if(!this.parchisCellService.findByPosition(parchisPiece.getPosicion()).isFinalCell()){
            parchisPiece.setCell(this.parchisCellService.findByPosition(nextPos));
            this.parchisPieceService.save(parchisPiece);
        }
    }
	public void canPlay(Integer dice,ParchisPiece piece) {

		Integer position=piece.getPosicion();

		int nextPos=position+dice;


		for(int i =position+1; i<=nextPos; i++) {
			ParchisCell cell=this.parchisCellService.findByPosition(i);
			if(cell.isBloqueo()) { //&&!cell.isFinalCell()
				piece.setCanPlay(false);
				break;
			}
			
			
			if(piece.getCell().isStair()) {
				if(piece.getColor().equals(Color.BLUE)&&nextPos>84) { 
					piece.setCanPlay(false);
				}
				else if(piece.getColor().equals(Color.YELLOW)&&nextPos>76) {
					piece.setCanPlay(false);
				}
				else if(piece.getColor().equals(Color.RED)&&nextPos>92) {
					piece.setCanPlay(false);
				}
				else if(piece.getColor().equals(Color.GREEN)&&nextPos>100) {
					piece.setCanPlay(false);
				}
				
			}

		}


	}


	public void sacarFichaDeCasa(ParchisPiece piece) {
		if(piece.getColor().equals(Color.BLUE) && !this.parchisCellService.findByPosition(22).isBloqueo()) {
			piece.setInBase(false);
            piece.setCell(this.parchisCellService.findByPosition(22));
			//return this.parchisCellService.findByPosition(22);
		}else if(piece.getColor().equals(Color.YELLOW) && !this.parchisCellService.findByPosition(5).isBloqueo()) {
			piece.setInBase(false);
            piece.setCell(this.parchisCellService.findByPosition(5));
			//return this.parchisCellService.findByPosition(5);
		}else if(piece.getColor().equals(Color.GREEN) && !this.parchisCellService.findByPosition(56).isBloqueo()) {
			piece.setInBase(false);
            piece.setCell(this.parchisCellService.findByPosition(56));
			//return this.parchisCellService.findByPosition(56);
		}else if(piece.getColor().equals(Color.RED) && !this.parchisCellService.findByPosition(39).isBloqueo()) {
			piece.setInBase(false);
            piece.setCell(this.parchisCellService.findByPosition(39));
			//return this.parchisCellService.findByPosition(39);
		}else {
            piece.setInBase(true);
			//return this.parchisCellService.findByPosition(piece.getStartCell());
		}
        this.parchisPieceService.save(piece);
        }

	


    public ParchisCell selectStart(Color c) {
        if(c==Color.YELLOW) {
            return this.parchisCellService.findByPosition(101);
        }else if(c==Color.GREEN) {
            return this.parchisCellService.findByPosition(102);
        }else if(c==Color.RED) {
            return this.parchisCellService.findByPosition(103);
        }else {
            return this.parchisCellService.findByPosition(104);
        }
}
}
