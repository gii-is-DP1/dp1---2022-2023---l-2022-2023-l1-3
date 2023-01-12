package org.springframework.samples.xtreme.game;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.xtreme.board.OcaBoardService;
import org.springframework.samples.xtreme.board.ParchisBoard;
import org.springframework.samples.xtreme.cells.ParchisCell;
import org.springframework.samples.xtreme.cells.ParchisCellService;
import org.springframework.samples.xtreme.chat.Chat;
import org.springframework.samples.xtreme.chat.ChatService;
import org.springframework.samples.xtreme.chat.Mensaje;
import org.springframework.samples.xtreme.chat.MensajeService;
import org.springframework.samples.xtreme.friendship.Friendship;
import org.springframework.samples.xtreme.friendship.FriendshipService;
import org.springframework.samples.xtreme.invitation.Invitation;
import org.springframework.samples.xtreme.invitation.InvitationService;
import org.springframework.samples.xtreme.oca.OcaPiece;
import org.springframework.samples.xtreme.oca.OcaPieceService;
import org.springframework.samples.xtreme.oca.OcaRules;
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
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(path="/games")
public class GameController {

    private static final String CREATE_GAME = "games/createGame";
    private static final String VIEW_GAMES = "games/viewGames";
    private static final String LOBBY_VIEW = "games/lobby";
    private static final String INVITE_FRIENDS = "games/inviteFriends";
    private static final String CREATE_INVITATION = "games/createInvitation";
    private static final String CHAT_GAME="games/chat";
    private static final String OCA_GAME="games/ocaGame";
    private static final String PARCHIS_GAME="games/parchisGame";
    private static final String PARCHIS_TURN="games/parchisTurn";
    private static final String WINNER="games/winner";


    private final GameService gameService;
    private final PlayerService playerService;
    private final FriendshipService friendshipService;
    private final InvitationService invitationService;
    private final MensajeService mensajeService;
    private final ChatService chatService;
    private final OcaPieceService ocaPieceService;
    private final OcaBoardService ocaBoardService;
    private final ParchisPieceService parchisPieceService;
    private final ParchisCellService parchisCellService;

    private UserUtils userUtils = new UserUtils();

    private String message=null;
    private Integer gameId;

    @Autowired
    public GameController(GameService gameService, PlayerService playerService, FriendshipService friendshipService,
            InvitationService invitationService,MensajeService mensajeService,ChatService chatService, 
            OcaPieceService ocaPieceService, OcaBoardService ocaBoardService, ParchisPieceService parchisPieceService, ParchisCellService parchisCellService){
        this.friendshipService = friendshipService;
        this.gameService = gameService;
        this.playerService = playerService;
        this.invitationService = invitationService;
        this.mensajeService=mensajeService;
        this.chatService=chatService;
        this.ocaPieceService = ocaPieceService;
        this.ocaBoardService = ocaBoardService;
        this.parchisPieceService = parchisPieceService;
        this.parchisCellService = parchisCellService;
    }

    @GetMapping(path="/createGame")
    public ModelAndView createGame() {
        ModelAndView mav = new ModelAndView(CREATE_GAME);
        mav.addObject("game",new Game());
      
        return mav;
    }

    @PostMapping(path = "/createGame")
    public ModelAndView createGame(@Valid @ModelAttribute("game") Game game, BindingResult res){
        ModelAndView mav = new ModelAndView("redirect:/"+CREATE_GAME);
       
        UserDetails currentUser = userUtils.getUserDetails();

        Player player = playerService.findByUsername(currentUser.getUsername());
        game.setCreatorPlayer(player);
        game.addPlayerToGame(player);

        if (res.hasErrors()||game.getGameName()==null||game.getCreatorPlayer()==null) {
            mav = new ModelAndView(CREATE_GAME);
            mav.addObject("game", game);
            
        } else {
            Chat chat = new Chat();
            game.setChat(chat);
            gameService.save(game);
            mav = new ModelAndView("redirect:/"+LOBBY_VIEW+"/"+game.getId());

        }
        return mav;
    }

   
    @GetMapping(path="/winner/{id}/{username}")
	public ModelAndView showWinner(@PathVariable("username") String username,@PathVariable("id") int id) {
		ModelAndView mav = new ModelAndView("games/winner");
		Player player = this.playerService.findByUsername(username);
		Game ocaGame = this.gameService.findGameById(id).get();
		ocaGame.setPlayerWinner(player);
        ocaGame.setStateGame(GameState.FINISHED);
        gameService.save(ocaGame);
		mav.addObject(ocaGame);
		mav.addObject(player);
		return mav;
	}

    @GetMapping(path="/lobby/{id}/chat")
    public ModelAndView chatGame(@PathVariable Integer id, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView(CHAT_GAME);
        response.addHeader("Refresh", "7");

        
        mav.addObject("id", id);
        Game game=gameService.findGameById(id).get();
        if(game.getChat().getMensaje() !=null){
        mav.addObject("chat", game.getChat().getMensaje());
        }
        return mav;
    }

    @PostMapping(path="/lobby/{id}/chat")
    public ModelAndView postChatGame(@Valid @ModelAttribute("mensaje") Mensaje mensaje, BindingResult res, @PathVariable Integer id){
        ModelAndView mav = new ModelAndView("redirect:/games/lobby/"+id+"/chat");
        if(mensaje.getText() != null){
            UserDetails currentUser = userUtils.getUserDetails();

            Player player = playerService.findByUsername(currentUser.getUsername());


            mensaje.setPlayer(player);
            Chat chat= gameService.findChatByGameId(id);
            mensaje.setChat(chat);
            mensaje.setText(mensaje.getText());
            chat.getMensaje().add(mensaje);
            chatService.save(chat);
        }
        return mav;
    }


    @GetMapping(path = "/joinGame")
    public ModelAndView listingGames(HttpServletResponse response){
        response.addHeader("Refresh", "5");
        ModelAndView mav = new ModelAndView(VIEW_GAMES);
        Collection<Game> games = gameService.getAll();
        List<Game> filter = games.stream().filter(x -> x.getIsPublic() == true && x.getStateGame().equals(GameState.WAITING_PLAYERS)).collect(Collectors.toList());
       
        mav.addObject("games",filter);
        
        UserDetails currentUser = userUtils.getUserDetails();

        Player player = playerService.findByUsername(currentUser.getUsername());
            
        mav.addObject("invitations", this.invitationService.findRecievedInvitationsByUsername(player.getUser().getUsername()));
        if(message != null){
            mav.addObject("message", message);
            message= null;
        }
        return mav;
    }

    @GetMapping(path = "/inviteFriends")
    public ModelAndView inivteFriends(){
        
        ModelAndView mav = new ModelAndView(INVITE_FRIENDS);
       
        UserDetails currentUser = userUtils.getUserDetails();

        Player player = playerService.findByUsername(currentUser.getUsername());

        Collection<Friendship> col = friendshipService.getAcceptedFriendshipsByUsername(currentUser.getUsername());
        List<Player> friends = col.stream().filter(x->x.getPlayer1().equals(player)).map(x->x.getPlayer2()).collect(Collectors.toList());    
        List<Player> friends2 = col.stream().filter(x->x.getPlayer2().equals(player)).map(x->x.getPlayer1()).collect(Collectors.toList());    
        friends.addAll(friends2);

        //Obtener solo los amigos online
        List<Player> online = friends.stream().filter(x-> x.getIsOnline() == true).collect(Collectors.toList());
        mav.addObject("friendsOnline", online);
        mav.addObject("gameId", gameId);
        
        return mav;
    }


    @GetMapping(path = "/inviteFriends/{username}")
    public ModelAndView createInvitation(@PathVariable String username){
       ModelAndView mav= new ModelAndView(CREATE_INVITATION);
       Player player = playerService.findByUsername(username);
       Game game = gameService.findGameByCreatorPlayer(player.getUser().getUsername());
       mav.addObject("player", player);
       mav.addObject("game", game);


       return mav;
    }

    @PostMapping(path="/inviteFriends/{username}")
    public ModelAndView createFriendshipAcceptedPost(@Valid @ModelAttribute("invitation") Invitation invitation, BindingResult res, @PathVariable("username") String username){

        UserDetails currentUser = userUtils.getUserDetails();

        Player player1 = playerService.findByUsername(currentUser.getUsername());
        Player player2 = this.playerService.findByUsername(username);

        Game game = gameService.findGameByCreatorPlayer(player1.getUser().getUsername());
        String id = game.getId().toString();
        ModelAndView mav = new ModelAndView("redirect:/games/lobby/"+id);
        Invitation exist_invitation= this.invitationService.findInvitationToGameByPlayers(player1.getId(), player2.getId(), game.getId());
        
        if(exist_invitation != null){
            return mav;
        }

        invitation.setGame(game);
        invitation.setPlayer1(player1);
        invitation.setPlayer2(player2);

        invitationService.save(invitation);

        return mav;
    }


    @GetMapping(path="/lobby/{id}")
    public ModelAndView lobby(@PathVariable Integer id, HttpServletResponse response) {

        Game game=gameService.findGameById(id).get();
        ModelAndView mav = new ModelAndView(LOBBY_VIEW);
        UserDetails currentUser = userUtils.getUserDetails();
        Player player = playerService.findByUsername(currentUser.getUsername());
        Invitation invitation= this.invitationService.findInvitationPlayerToGame(player.getId(), id);


        if(game.getStateGame().equals(GameState.FINISHED)){
            mav= new ModelAndView(WINNER);
            mav.addObject("player", game.getPlayerWinner());
            return mav;
        }
        if(game.getPlayers().size()==game.getNumPlayers()){
            game.setStateGame(GameState.STARTED);
            gameService.save(game);
        }


        if(!game.getPlayers().contains(player) && ((game.getPlayers().size() == game.getNumPlayers()) || !(game.getStateGame().equals(GameState.WAITING_PLAYERS)))){
            mav = new ModelAndView("redirect:/games/joinGame");
            message="La partida a la que ha intentado unirse está llena o ya ha empezado";
            return mav;
        }

        if(!game.getPlayers().contains(player) && (invitation == null && !game.getIsPublic())){
            mav = new ModelAndView("redirect:/games/joinGame");
            message="Está intentando unirse a una partida privada sin invitación";
            return mav;
        }

        // partida no empezada
        if(game.getStateGame().equals(GameState.WAITING_PLAYERS)) {
            gameId=id;
            response.addHeader("Refresh", "5");

            if(!game.getPlayers().contains(player)) {
                game.addPlayerToGame(player);
                gameService.save(game);
            }
        
            mav.addObject("game",game);
            return mav;
        
        // oca
        } else if(game.getStateGame().equals(GameState.STARTED) && game.getGameType().equals(GameType.OCA)) {
            response.addHeader("Refresh", "3");

            OcaPiece piece =ocaPieceService.findPiecebyGameAndPlayer(player.getId(), id);
            if(piece==null) {
		        piece = new OcaPiece();
                piece.setPlayer(player);
                piece.setGame(gameService.findGameById(id).get());
                this.ocaPieceService.save(piece);
            }

            mav = new ModelAndView(OCA_GAME);
		
            OcaPiece newPiece=this.ocaPieceService.findByPlayerId(player.getId());
		    if(newPiece!=null) {
			    piece = newPiece;
		    }

            for(int j=0;j <= game.getPlayers().size()-1;j++){
                mav.addObject("player"+j, game.getPlayers().get(j));
                mav.addObject("piece"+j, this.ocaPieceService.findPiecebyGameAndPlayer(game.getPlayers().get(j).getId(), id));
            }

            mav.addObject("isUserEquals",player==game.getPlayers().get(game.getI()));
		    mav.addObject("piece", piece);
		    mav.addObject("player", player);
		    mav.addObject("game", this.gameService.findGameById(id).get());
		    mav.addObject("board", this.ocaBoardService.findById(id));
		    return mav;
	
        } else if(game.getStateGame().equals(GameState.STARTED) && game.getGameType().equals(GameType.PARCHIS)) {
            response.addHeader("Refresh", "3");

            mav = new ModelAndView(PARCHIS_GAME);

            List<ParchisPiece> pieces = new ArrayList<>(parchisPieceService.findPiecesByGameAndPlayer(id, player.getId()));

            if (pieces.isEmpty()) {
                Color color = null;
                ParchisCell c = new ParchisCell();
                Integer playerIndex = game.getPlayers().indexOf(player);
                switch(playerIndex) {
                    case 0: // Jugador 1, AMARILLO
                        color = Color.YELLOW;
                        c = this.parchisCellService.findByPosition(101);
                        break;
                    case 1: // Jugador 2, VERDE
                        color = Color.GREEN;
                        c = this.parchisCellService.findByPosition(102);
                        break;
                    case 2: // Jugador 3, ROJO
                        color = Color.RED;
                        c = this.parchisCellService.findByPosition(103);
                        break;
                    case 3: // Jugador 4, AZUL
                        color = Color.BLUE;
                        c = this.parchisCellService.findByPosition(104);
                        break;
                }

                List<ParchisPiece> piecesTemp = c.getPieces();
                for(int i = 0; i < 4; i++) {
                    ParchisPiece p = new ParchisPiece();
                    p.setPlayer(player);
                    p.setColor(color);
                    p.setGame(game);
                    p.setName("PIECE " + i);
                    p.setCell(c);
                    p.setInBase(true);
                    piecesTemp.add(p);
                    this.parchisPieceService.save(p);
                    player.addParchisPiecesToGame(p);
                    this.playerService.save(player);
                    game.addParchisPiecesToGame(p);
                    this.gameService.save(game);
                }

                c.setPieces(piecesTemp);
                this.parchisCellService.save(c);
            }

            // MOSTRAR LA POSICION DE LAS PIEZAS DE TODOS LOS JUGADORES
            for(int j=0;j <= game.getPlayers().size()-1;j++){
                List<ParchisPiece> playerPieces = new ArrayList<>();
                mav.addObject("player"+j, game.getPlayers().get(j));
                for(int i = 0; i <= pieces.size()-1; i++) {
                    playerPieces.add(pieces.get(i));
                }
                mav.addObject("pieces"+j, playerPieces);
            }

            mav.addObject("isUserEquals",player==game.getPlayers().get(game.getI()));
		    mav.addObject("player", player);
		    mav.addObject("game", this.gameService.findGameById(id).get());
		    return mav;
        }
        else{
            return mav;
        }
    }

    @PostMapping(path = "/lobby/{id}")
    public ModelAndView deleteLobby(@Valid @ModelAttribute("game") Game game, BindingResult res,@PathVariable Integer id){
        ModelAndView mav = new ModelAndView("redirect:/home");

        UserDetails currentUser = userUtils.getUserDetails();

        Player player = playerService.findByUsername(currentUser.getUsername());

        Boolean isHost= gameService.findGameById(id).get().getCreatorPlayer().equals(player);
        
        if(isHost){
            gameService.deleteGame(id);
        }else{
            game = gameService.findGameById(id).get();
            game.removePlayerToGame(player);
            gameService.save(game);
        }

        return mav;
        
    }

    @GetMapping(path="/{id}/{playerId}")
	public ModelAndView playTurn(@PathVariable("id") int id,@PathVariable("playerId") Integer playerId){

        ModelAndView mav = new ModelAndView("redirect:/home");
        Game game= gameService.findGameById(id).get();

        if (game.getGameType().equals(GameType.PARCHIS)) {

            mav = new ModelAndView(PARCHIS_TURN);

            Player player = playerService.findById(playerId);
            game.throwDice();
            gameService.save(game);
            Integer dice = game.getDice();

            for(ParchisPiece p:player.getParchisPieces()) {
                p.setCanMove(true);
                if(!this.parchisCellService.findByPosition(p.getCell().getPosition()).isHouse()) {
                    Integer pos=p.getCell().getPosition();
                    Integer n=pos+1;
            
                    int nextPos=pos+dice;
                    if(p.getCell().isHouse()&&dice!=5) {
                        p.setCanMove(false);
                    }
            
                    for(int i = n;i<=nextPos;i++) {
                        ParchisCell cell=this.parchisCellService.findByPosition(i);
                        if(cell.isBloqueo()&&!cell.isFinalCell()) { 
                            p.setCanMove(false);
                            break;
                        }
                        
                        
                        if(p.getCell().isStair()) {
                            if(p.getColor().equals(Color.GREEN)&&nextPos>84) { 
                                p.setCanMove(false);
                            }
                            else if(p.getColor().equals(Color.YELLOW)&&nextPos>76) {
                                p.setCanMove(false);
                            }
                            else if(p.getColor().equals(Color.RED)&&nextPos>92) {
                                p.setCanMove(false);
                            }
                            else if(p.getColor().equals(Color.BLUE)&&nextPos>100) {
                                p.setCanMove(false);
                            }
                            
                        }
            
                    }
                }
                else if(this.parchisCellService.findByPosition(p.getCell().getPosition()).isHouse()) {
                    
                    if(p.getColor().equals(Color.BLUE)) { 
                        if(this.parchisCellService.findByPosition(22).isBloqueo()) {
                            p.setCanMove(false);
                        }
                        
                    }
                    else if(p.getColor().equals(Color.YELLOW)) {
                        if(this.parchisCellService.findByPosition(5).isBloqueo()) {
                            p.setCanMove(false);
                        }
                    }
                    else if(p.getColor().equals(Color.RED)) {
                        if(this.parchisCellService.findByPosition(39).isBloqueo()) {
                            p.setCanMove(false);
                        }
                    }
                    else if(p.getColor().equals(Color.GREEN)) {
                        if(this.parchisCellService.findByPosition(56).isBloqueo()) {
                            p.setCanMove(false);
                        }
                    }
                    
                    this.parchisPieceService.save(p);
                }
                this.parchisPieceService.save(p);
            }
            
            List<ParchisPiece> pieces = new ArrayList<>(parchisPieceService.findPiecesByGameAndPlayer(id, player.getId()));

            // MOSTRAR LA POSICION DE LAS PIEZAS DE TODOS LOS JUGADORES
            for(int j=0;j <= game.getPlayers().size()-1;j++){
                List<ParchisPiece> playerPieces = new ArrayList<>();
                mav.addObject("player"+j, game.getPlayers().get(j));
                for(int i = 0; i <= pieces.size()-1; i++) {
                    playerPieces.add(pieces.get(i));
                }
                mav.addObject("pieces"+j, playerPieces);
            }

            for(int n=0;n<=pieces.size()-1;n++) {
                mav.addObject("piece"+n,pieces.get(n));
            }

            mav.addObject("isUserEquals",player==game.getPlayers().get(game.getI()));
            mav.addObject("game", this.gameService.findGameById(id).get());
            mav.addObject("player", player);
            mav.addObject("dice", dice);

            return mav;

        } else if (game.getGameType().equals(GameType.OCA)) {
            OcaPiece piece = this.ocaPieceService.findByPlayerId(playerId);

            if(piece.getPenalization()<=0) {
                
                game.throwDice();
                gameService.save(game);
                Integer dice = game.getDice();
                if ((piece.getPosition()+dice)==63||piece.getPosition()==63) {
                    game.setStateGame(GameState.FINISHED);
                    game.setPlayerWinner(this.playerService.findById(playerId));
                    gameService.save(game);
                    return new ModelAndView("redirect:/games/lobby/"+id);
                }else {
                    if (piece.getPosition()+dice>63) {
                        piece.setPosition(63-(piece.getPosition()+dice-63));
                    }else {
                        piece.setPosition(piece.getPosition()+dice);
                    }
                }
    
                Integer position=piece.getPosition();
    
                Integer penalizacion=OcaRules.getpen(position);
                if(penalizacion>0){
                        piece.setPenalization(penalizacion);
                    }
                
                if(OcaRules.isLabyrinth(position)) {
                    piece.setPosition(OcaRules.labyrinth(position));
                }
                if(OcaRules.isDeath(position)) {
                    piece.setPosition(OcaRules.death(position));
                }
                if(OcaRules.repeatTurn(position)) {
                    if(OcaRules.isOca(position)) {
                        piece.setPosition(OcaRules.oca(position));					
                    }
                    else if(OcaRules.isDices(position)) {
                        piece.setPosition(OcaRules.dices(position));					
                    }
                    else if(OcaRules.isBridge(position)) {
                        piece.setPosition(OcaRules.bridge(position));				
                    }
                    this.ocaPieceService.save(piece);
                    return new ModelAndView("redirect:/games/lobby/"+id);			
                }
    
                game.nextTurn();
    
                    this.ocaPieceService.save(piece);
                    //this.gameService.save(game);
                        
                return new ModelAndView("redirect:/games/lobby/"+id);
                        
            }
            else {
                piece.setPenalization(piece.getPenalization()-1);
                
                this.ocaPieceService.save(piece);
                game.nextTurn();		
                this.gameService.save(game);
                
                return new ModelAndView("redirect:/games/lobby/"+id);		
            }
        }
        return mav;
	}

    @GetMapping(path="/{id}/{playerId}/{dice}/{pieceId}")
	public ModelAndView movePiece (@PathVariable("id") int id,@PathVariable("playerId") Integer playerId,@PathVariable("dice") Integer dice, @PathVariable("pieceId") int pieceId) {
		ParchisPiece piece = this.parchisPieceService.findById(pieceId);
		ParchisCell nextCell = new ParchisCell();
        Game game= gameService.findGameById(id).get();

		if(dice!=0) {
            ParchisCell currentCell = piece.getCell();
            currentCell.quitarFicha(piece);
            this.parchisCellService.save(currentCell);
            
            if(dice==5&&currentCell.isHouse()) {
                piece.setInBase(true);
                if(currentCell.isHouse()&&Color.BLUE==piece.getColor()&&!this.parchisCellService.findByPosition(56).isBloqueo()) {
                    piece.setInBase(false);
                    this.parchisPieceService.save(piece);
                    nextCell = this.parchisCellService.findByPosition(56);
                }else if(currentCell.isHouse()&&Color.YELLOW==piece.getColor()&&!this.parchisCellService.findByPosition(5).isBloqueo()) {
                    piece.setInBase(false);
                    this.parchisPieceService.save(piece);
                    nextCell = this.parchisCellService.findByPosition(5);
                }else if(currentCell.isHouse()&&Color.GREEN==piece.getColor()&&!this.parchisCellService.findByPosition(22).isBloqueo()) {
                    piece.setInBase(false);
                    this.parchisPieceService.save(piece);
                    nextCell = this.parchisCellService.findByPosition(22);
                }else if(currentCell.isHouse()&&Color.RED==piece.getColor()&&!this.parchisCellService.findByPosition(39).isBloqueo()) {
                    piece.setInBase(false);
                    this.parchisPieceService.save(piece);
                    nextCell = this.parchisCellService.findByPosition(39);
                }else {
                    nextCell = this.parchisCellService.findByPosition(piece.getBaseCell());
                }
            }else if(!piece.getInBase()){
                ParchisCell crossCell = null;
                Integer pos=piece.getPosicion();
                int nextPos=pos+dice;
                for(int i = pos;i<nextPos;i++) {
                    ParchisCell cell=this.parchisCellService.findByPosition(i);
        
                    if(cell.getColor().equals(Color.BLUE)&&cell.getPosition()==51) { 
                        crossCell = cell;
                    }
                    else if(cell.getColor().equals(Color.YELLOW)&&cell.getPosition()==68) {
                        crossCell = cell;
                    }
                    else if(cell.getColor().equals(Color.RED)&&cell.getPosition()==34) {
                        crossCell = cell;
                    }
                    else if(cell.getColor().equals(Color.GREEN)&&cell.getPosition()==17) {
                        crossCell = cell;
                    }
                    else if(!(cell.getColor().equals(Color.YELLOW))&&cell.getPosition()==68) {
                        crossCell = cell;
                    }
        
                }
                
                if(crossCell==null) {
                    nextCell = this.parchisCellService.findByPosition(currentCell.getPosition() + dice);
                    if(nextCell.getPieces().size()==1) {
                        // nextCell = eatPiece
                    }
                    
                } else {	
                    Integer distancia= Math.abs(crossCell.getPosition()-currentCell.getPosition()+1);
                    
                    if(piece.getColor().equals(Color.YELLOW)) { 
                        nextCell=this.parchisCellService.findByPosition(69);
                    }
                    else if(piece.getColor().equals(Color.GREEN)) {
                        nextCell=this.parchisCellService.findByPosition(77);
                    }
                    else if(piece.getColor().equals(Color.RED)) {
                        nextCell=this.parchisCellService.findByPosition(85);
                    }
                    else if(piece.getColor().equals(Color.BLUE)) {
                        nextCell=this.parchisCellService.findByPosition(93);
                    }
                    if(!piece.getColor().equals(Color.YELLOW)&&crossCell.getPosition()==68) {
                        nextCell=this.parchisCellService.findByPosition(1);
                    } 
                    
                    
                    Integer newDiceNumber=dice-distancia;
                    nextCell = this.parchisCellService.findByPosition(nextCell.getPosition()+newDiceNumber);
                }
                
            }else {
                nextCell=currentCell;
            }
            
            piece.setCell(nextCell);
            nextCell.colocarFicha(piece);
		}

		this.parchisCellService.save(nextCell);
		
		if(nextCell.isFinalCell()&&nextCell.getPieces().size()>=4) {
            return new ModelAndView("redirect:/winner/" + id);
        }else if(nextCell.isFinalCell()&&nextCell.getPieces().size()<4) {
            piece.setCanMove(false);
    		this.parchisPieceService.save(piece);
            // return new ModelAndView("redirect:/parchisTurn/"+parchisGameId+"/"+playerId+"/move10"); // Falta funcion
        }
		this.parchisPieceService.save(piece);

        game.nextTurn();

        this.gameService.save(game);
        return new ModelAndView("redirect:/games/lobby/"+id);
	}



    @GetMapping(path="/winner/{id}")
    public ModelAndView gameWinner(@PathVariable("id") int id) {
        ModelAndView mav = new ModelAndView(WINNER);     
        return mav;
    }

}
