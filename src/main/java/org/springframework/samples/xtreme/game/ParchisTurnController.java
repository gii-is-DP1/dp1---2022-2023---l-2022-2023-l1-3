package org.springframework.samples.xtreme.game;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
            
            if(game.getDice() != 5) {
                if(game.getDice() == 6){
                    return new ModelAndView("redirect:/parchis/"+gameId+"/"+playerId);		
                } 
                p.setCanPlay(false);
                game.nextTurn();
                this.gameService.save(game);
                return new ModelAndView("redirect:/games/lobby/"+gameId);


            } else{ //  if(game.getDice() == 5)
                sacarFichaDeCasa(p,game);
                game.nextTurn();
                this.gameService.save(game);
                return new ModelAndView("redirect:/games/lobby/"+gameId);

            }
        } else{
                Integer position=p.getPosicion();
                Integer nextPos=position+dice;

                
                avanzar(nextPos, p,game);
   
                p= this.parchisPieceService.findById(p.getId());
                if(winner(currentPlayer,game,p) ){
                    game.setPlayerWinner(currentPlayer);
                    game.setStateGame(GameState.FINISHED);
                    this.gameService.save(game);
                    return new ModelAndView("redirect:/games/lobby/"+gameId);
                }
                    
                if(game.getDice() == 6){
                    return new ModelAndView("redirect:/parchis/"+gameId+"/"+playerId);		
                } 
                game.nextTurn();
                this.gameService.save(game);
                return new ModelAndView("redirect:/games/lobby/"+gameId);			
        }
    }
    public void eat(Integer nextPos, Integer parchisPieceId,Game game){
        ParchisBoard board= this.gameService.findGameById(game.getId()).get().getParchisBoard();
        ParchisPiece piece= this.parchisPieceService.findById(parchisPieceId);
        ParchisCell cell = piece.getCell();
        List<ParchisPiece> pieces = this.parchisPieceService.findPieceByCellAndBoard(cell.getPosition(), board.getId());
        if(!cell.isSafe() && !cell.isStart() && pieces.size() == 2 && !pieces.get(0).getColor().equals(pieces.get(1).getColor())){
            
            ParchisPiece piece2=pieces.stream().filter(x->x != piece).collect(Collectors.toList()).get(0);
            piece2.setCell(this.parchisCellService.findByPosition(piece2.getBaseCell()));
            piece2.setInBase(true);
            this.parchisPieceService.save(piece2);

            avanzar(piece.getPosicion()+20, piece, game);
        }

    }


    public void avanzar(Integer nextPos, ParchisPiece parchisPiece,Game game){

        // linea de meta amarilla
        if(parchisPiece.getColor().equals(Color.YELLOW) && parchisPiece.getPosicion()>=69 && parchisPiece.getPosicion()<=75){
            if(76 - (parchisPiece.getPosicion() + game.getDice()) > 0){
                parchisPiece.setCell(this.parchisCellService.findByPosition(nextPos));
                this.parchisPieceService.save(parchisPiece);
            } else if(76 - (parchisPiece.getPosicion() + game.getDice()) == 0){
                parchisPiece.setCell(this.parchisCellService.findByPosition(nextPos));
                this.parchisPieceService.save(parchisPiece);
                List<ParchisPiece> pieces = this.parchisPieceService.findPieceByBoardAndPlayer(parchisPiece.getPlayer().getId(), game.getParchisBoard().getId());
                ParchisPiece piece2=pieces.stream().filter(x-> x!= parchisPiece && x.getPosicion()!=76 && x.getPosicion()!=101).findFirst().orElse(null);
                if(piece2 != null){
                    avanzar(piece2.getPosicion() + 10, piece2, game);
                }
            }
        }
        
        // linea de meta verde
        else if(parchisPiece.getColor().equals(Color.GREEN) && parchisPiece.getPosicion()>=77 && parchisPiece.getPosicion()<=83){
            if(84 - (parchisPiece.getPosicion() + game.getDice()) > 0){
                parchisPiece.setCell(this.parchisCellService.findByPosition(nextPos));
                this.parchisPieceService.save(parchisPiece);
            } else if(84 - (parchisPiece.getPosicion() + game.getDice()) == 0){
                parchisPiece.setCell(this.parchisCellService.findByPosition(nextPos));
                this.parchisPieceService.save(parchisPiece);
                List<ParchisPiece> pieces = this.parchisPieceService.findPieceByBoardAndPlayer(parchisPiece.getPlayer().getId(), game.getParchisBoard().getId());
                ParchisPiece piece2=pieces.stream().filter(x-> x!= parchisPiece && x.getPosicion()!=84 && x.getPosicion()!=102).findFirst().orElse(null);
                if(piece2 != null){
                    avanzar(piece2.getPosicion() + 10, piece2, game);
                } 
            }
        }

        // linea de meta roja
        else if(parchisPiece.getColor().equals(Color.RED) && parchisPiece.getPosicion()>=85 && parchisPiece.getPosicion()<=91){
            if(92 - (parchisPiece.getPosicion() + game.getDice()) > 0){
                parchisPiece.setCell(this.parchisCellService.findByPosition(nextPos));
                this.parchisPieceService.save(parchisPiece);
            }else if(92 - (parchisPiece.getPosicion() + game.getDice()) == 0){
                parchisPiece.setCell(this.parchisCellService.findByPosition(nextPos));
                this.parchisPieceService.save(parchisPiece);
                List<ParchisPiece> pieces = this.parchisPieceService.findPieceByBoardAndPlayer(parchisPiece.getPlayer().getId(), game.getParchisBoard().getId());
                ParchisPiece piece2=pieces.stream().filter(x-> x!= parchisPiece && x.getPosicion()!=92 && x.getPosicion()!=103).findFirst().orElse(null);
                if(piece2 != null){
                    avanzar(piece2.getPosicion() + 10, piece2, game);
                }
            }
        }

        // linea de meta azul
        else if(parchisPiece.getColor().equals(Color.BLUE) && parchisPiece.getPosicion()>=93 && parchisPiece.getPosicion()<=99){
            if(100 - (parchisPiece.getPosicion() + game.getDice()) > 0){
                parchisPiece.setCell(this.parchisCellService.findByPosition(nextPos));
                this.parchisPieceService.save(parchisPiece);
            }else if(100 - (parchisPiece.getPosicion() + game.getDice()) == 0){
                parchisPiece.setCell(this.parchisCellService.findByPosition(nextPos));
                this.parchisPieceService.save(parchisPiece);
                List<ParchisPiece> pieces = this.parchisPieceService.findPieceByBoardAndPlayer(parchisPiece.getPlayer().getId(), game.getParchisBoard().getId());
                ParchisPiece piece2=pieces.stream().filter(x-> x!= parchisPiece && x.getPosicion()!=100  && x.getPosicion()!=104).findFirst().orElse(null);
                if(piece2 != null){
                    avanzar(piece2.getPosicion() + 10, piece2, game);
                }
            }
        }

        // si esta en otra posicion
        else if(!this.parchisCellService.findByPosition(parchisPiece.getPosicion()).isFinalCell()){
            Boolean b= false;
            for(int i =parchisPiece.getPosicion()+1; i<=nextPos; i++) {
                ParchisCell cell=this.parchisCellService.findByPosition(i);
                List<ParchisPiece> pieces = this.parchisPieceService.findPieceByCellAndBoard(cell.getPosition(), game.getParchisBoard().getId());
                // se comprueba que no hay bloqueos
                if(pieces.size() == 2){
                    b=true;
                }
                // meter en la linea de meta a las fichas verdes
                else if(!b && parchisPiece.getColor().equals(Color.GREEN) && cell.getPosition()==18) {
                    b=true;
                    if(nextPos - i<= 7){ // para que no se salga de cells.size() si comes una ficha justo antes de entrar por ejemplo
                    List<ParchisCell> cells = this.parchisCellService.findCellGreen();
                    parchisPiece.setCell(this.parchisCellService.findByPosition(cells.get(nextPos - i ).getPosition()));
                    this.parchisPieceService.save(parchisPiece);
                    }
                }
                // meter en la linea de meta a las fichas rojas
                else if(!b && parchisPiece.getColor().equals(Color.RED) && cell.getPosition()==35) {
                    b=true;
                    if(nextPos - i<= 7){
                    List<ParchisCell> cells = this.parchisCellService.findCellRed();
                    parchisPiece.setCell(this.parchisCellService.findByPosition(cells.get(nextPos - i).getPosition()));
                    this.parchisPieceService.save(parchisPiece);
                    }
                }
                // meter en la linea de meta a las fichas azules
                else if(!b && parchisPiece.getColor().equals(Color.BLUE) && cell.getPosition()==52) {
                    b=true;
                    if(nextPos - i<= 7){
                    List<ParchisCell> cells = this.parchisCellService.findCellBlue();
                    parchisPiece.setCell(this.parchisCellService.findByPosition(cells.get(nextPos - i).getPosition()));
                    this.parchisPieceService.save(parchisPiece);
                    }
                }
                // meter en la linea de meta a las fichas amarillas
                else if(!b && parchisPiece.getColor().equals(Color.YELLOW) && cell.getPosition()==69) {
                    b=true;
                    if(nextPos - i<= 7){
                    List<ParchisCell> cells = this.parchisCellService.findCellYellow();
                    parchisPiece.setCell(this.parchisCellService.findByPosition(cells.get(nextPos - i).getPosition()));
                    this.parchisPieceService.save(parchisPiece);
                    }
                }
                // otro caso 
                else if(!b && nextPos==i){
                    if(nextPos>68){
                        nextPos-=68;

                    }
                    parchisPiece.setCell(this.parchisCellService.findByPosition(nextPos));
                    this.parchisPieceService.save(parchisPiece); 
                }
            }
        }
        eat(nextPos, parchisPiece.getId(),game);
    }


	public void sacarFichaDeCasa(ParchisPiece piece,Game game) {
		if(piece.getColor().equals(Color.GREEN)) {
            List<ParchisPiece> pieces = this.parchisPieceService.findPieceByCellAndBoard(22, game.getParchisBoard().getId());
            if(pieces.size() < 2 ){
                piece.setInBase(false);
                piece.setCell(this.parchisCellService.findByPosition(22));
            }
		}else if(piece.getColor().equals(Color.YELLOW)) {
            List<ParchisPiece> pieces = this.parchisPieceService.findPieceByCellAndBoard(5, game.getParchisBoard().getId());
            if(pieces.size() < 2 ){
                piece.setInBase(false);
                piece.setCell(this.parchisCellService.findByPosition(5));
            }
		}else if(piece.getColor().equals(Color.BLUE)) {
            List<ParchisPiece> pieces = this.parchisPieceService.findPieceByCellAndBoard(56, game.getParchisBoard().getId());
            if(pieces.size() < 2 ){
                piece.setInBase(false);
                piece.setCell(this.parchisCellService.findByPosition(56));
            }
		}else if(piece.getColor().equals(Color.RED)) {
            List<ParchisPiece> pieces = this.parchisPieceService.findPieceByCellAndBoard(39, game.getParchisBoard().getId());
            if(pieces.size() < 2 ){
                piece.setInBase(false);
                piece.setCell(this.parchisCellService.findByPosition(39));
            }
		}else {
            piece.setInBase(true);
		}
        this.parchisPieceService.save(piece);
        }

        public Boolean winner(Player player, Game game, ParchisPiece parchisPiece){
            List<ParchisPiece> pieces= this.parchisPieceService.findPieceByBoardAndPlayer(player.getId(), game.getParchisBoard().getId());
            if(parchisPiece.getColor().equals(Color.YELLOW) && pieces.stream().allMatch(x->x.getPosicion()==76)){
                return true;
            } else if(parchisPiece.getColor().equals(Color.GREEN) && pieces.stream().allMatch(x->x.getPosicion()==84)){
                return true;
            } else if(parchisPiece.getColor().equals(Color.RED) && pieces.stream().allMatch(x->x.getPosicion()==92)){
                return true;
            } else if(parchisPiece.getColor().equals(Color.BLUE) && pieces.stream().allMatch(x->x.getPosicion()==100)){
                return true;
            }
            return false;
        }
}
