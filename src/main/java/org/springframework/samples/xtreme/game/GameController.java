package org.springframework.samples.xtreme.game;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.xtreme.board.OcaBoardService;
import org.springframework.samples.xtreme.board.ParchisBoard;
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
    private static final String WINNER="games/winner";


    private final GameService gameService;
    private final PlayerService playerService;
    private final FriendshipService friendshipService;
    private final InvitationService invitationService;
    private final MensajeService mensajeService;
    private final ChatService chatService;
    private final OcaPieceService ocaPieceService;
    private final OcaBoardService ocaBoardService;

    private UserUtils userUtils = new UserUtils();

    private String message=null;
    private Integer gameId;
    @Autowired
    public GameController(GameService gameService, PlayerService playerService, FriendshipService friendshipService,
            InvitationService invitationService,MensajeService mensajeService,ChatService chatService, 
            OcaPieceService ocaPieceService, OcaBoardService ocaBoardService){
        this.friendshipService = friendshipService;
        this.gameService = gameService;
        this.playerService = playerService;
        this.invitationService = invitationService;
        this.mensajeService=mensajeService;
        this.chatService=chatService;
        this.ocaPieceService = ocaPieceService;
        this.ocaBoardService = ocaBoardService;

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


        if(!game.getPlayers().contains(player) &&
         ((game.getPlayers().size() == game.getNumPlayers()) || !(game.getStateGame().equals(GameState.WAITING_PLAYERS)))){
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
        if(game.getStateGame().equals(GameState.WAITING_PLAYERS)){
            gameId=id;
            response.addHeader("Refresh", "5");

            if(!game.getPlayers().contains(player)){
                game.addPlayerToGame(player);
                gameService.save(game);
            }
        
        mav.addObject("game",game);
        return mav;
        
        // oca
        }else if(game.getStateGame().equals(GameState.STARTED) && game.getGameType().equals(GameType.OCA)){
            response.addHeader("Refresh", "3");

           OcaPiece piece =ocaPieceService.findPiecebyGameAndPlayer(player.getId(), id);
           if(piece==null){
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
	public ModelAndView ocaPlayTurn(@PathVariable("id") int id,@PathVariable("playerId") Integer playerId){
		OcaPiece piece = this.ocaPieceService.findByPlayerId(playerId);
		Game game= gameService.findGameById(id).get();

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

    @GetMapping(path="/winner/{id}")
    public ModelAndView gameWinner(@PathVariable("id") int id) {
        ModelAndView mav = new ModelAndView(WINNER);     
        return mav;
    }

}
