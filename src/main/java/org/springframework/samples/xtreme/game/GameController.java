package org.springframework.samples.xtreme.game;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.xtreme.board.ParchisBoard;
import org.springframework.samples.xtreme.chat.Chat;
import org.springframework.samples.xtreme.chat.ChatService;
import org.springframework.samples.xtreme.chat.Mensaje;
import org.springframework.samples.xtreme.chat.MensajeService;
import org.springframework.samples.xtreme.friendship.Friendship;
import org.springframework.samples.xtreme.friendship.FriendshipService;
import org.springframework.samples.xtreme.invitation.Invitation;
import org.springframework.samples.xtreme.invitation.InvitationService;
import org.springframework.samples.xtreme.player.Player;
import org.springframework.samples.xtreme.player.PlayerService;
import org.springframework.security.core.context.SecurityContextHolder;
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

    private final GameService gameService;
    private final PlayerService playerService;
    private final FriendshipService friendshipService;
    private final InvitationService invitationService;
    private final MensajeService mensajeService;
    private final ChatService chatService;


    private static final String CREATE_GAME = "games/createGame";
    private static final String VIEW_GAMES = "games/viewGames";
    private static final String LOBBY_VIEW = "games/lobby";
    private static final String INVITE_FRIENDS = "games/inviteFriends";
    private static final String CREATE_INVITATION = "games/createInvitation";
    private static final String CHAT_GAME="games/chat";


    @Autowired
    public GameController(GameService gameService, PlayerService playerService, FriendshipService friendshipService,
            InvitationService invitationService,MensajeService mensajeService,ChatService chatService){
        this.friendshipService = friendshipService;
        this.gameService = gameService;
        this.playerService = playerService;
        this.invitationService = invitationService;
        this.mensajeService=mensajeService;
        this.chatService=chatService;

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
       
        Object principal=SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails=null;
        if (principal instanceof UserDetails) {
            userDetails = (UserDetails) principal;
            Player player = playerService.findByUsername(userDetails.getUsername());
            game.setCreatorPlayer(player);

            game.addPlayerToGame(player);
        }

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

    @GetMapping(path="/lobby/{id}")
    public ModelAndView lobby(@PathVariable Integer id, HttpServletResponse response) {
        Game game=gameService.findGameById(id).get();
        ModelAndView mav = new ModelAndView(LOBBY_VIEW);

        Object principal=SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails=(UserDetails) principal;
        Player player = playerService.findByUsername(userDetails.getUsername());

        if(!game.getPlayers().contains(player) &&
         ((game.getPlayers().size() == game.getNumPlayers()) || !(game.getStateGame().equals(GameState.WAITING_PLAYERS)))){
            mav = new ModelAndView("redirect:/games/joinGame");
            return mav;
        }

        response.addHeader("Refresh", "5");

        Boolean isHost=false;
        isHost= gameService.findGameById(id).get().getCreatorPlayer().equals(player);

        if(!game.getPlayers().contains(player)){
            game.addPlayerToGame(player);
            gameService.save(game);
        }

        mav.addObject("game",game);
        mav.addObject("numActualPlayers",game.getPlayers().size());
        mav.addObject("isHost", isHost);

        return mav;
    }
    @PostMapping(path = "/lobby/{id}")
    public ModelAndView deleteLobby(@Valid @ModelAttribute("game") Game game, BindingResult res,@PathVariable Integer id){
        ModelAndView mav = new ModelAndView("redirect:/users/home");

        Object principal=SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails) principal;
        String usuarioActual = userDetails.getUsername();

        Player player = playerService.findByUsername(usuarioActual);
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
            Object principal=SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            UserDetails userDetails = (UserDetails) principal;
            String usuarioActual = userDetails.getUsername();
            Player player = playerService.findByUsername(usuarioActual);


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
        response.addHeader("Refresh", "2");
        ModelAndView mav = new ModelAndView(VIEW_GAMES);
        Collection<Game> games = gameService.getAll();
        List<Game> filter = games.stream().filter(x -> x.getIsPublic() == true && x.getStateGame().equals(GameState.WAITING_PLAYERS)).collect(Collectors.toList());
       
        mav.addObject("games",filter);
        
        Object principal=SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails=null;
        if (principal instanceof UserDetails) {
            userDetails = (UserDetails) principal;
            Player p= playerService.findByUsername(userDetails.getUsername());
            mav.addObject("invitations", this.invitationService.findRecievedInvitationsByUsername(p.getUser().getUsername()));
        }
        return mav;
    }

    @GetMapping(path = "/inviteFriends")
    public ModelAndView inivteFriends(){
        
        ModelAndView mav = new ModelAndView(INVITE_FRIENDS);
        // obtener el usuario actualmente logueado
        Object principal=SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails=null;
        if (principal instanceof UserDetails) {
            userDetails = (UserDetails) principal;
            Player p= playerService.findByUsername(userDetails.getUsername());
            Collection<Friendship> col = friendshipService.getAcceptedFriendshipsByUsername(userDetails.getUsername());
            List<Player> friends = col.stream().filter(x->x.getPlayer1().equals(p)).map(x->x.getPlayer2()).collect(Collectors.toList());    
            List<Player> friends2 = col.stream().filter(x->x.getPlayer2().equals(p)).map(x->x.getPlayer1()).collect(Collectors.toList());    
            friends.addAll(friends2);

            //Obtener solo los amigos online
            List<Player> online = friends.stream().filter(x-> x.getIsOnline() == true).collect(Collectors.toList());
            mav.addObject("friendsOnline", online);
        }
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

        Object principal=SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails) principal;

        Player player1 = playerService.findByUsername(userDetails.getUsername());
        Player player2 = this.playerService.findByUsername(username);

        Game game = gameService.findGameByCreatorPlayer(player1.getUser().getUsername());
        String id = game.getId().toString();
        ModelAndView mav = new ModelAndView("redirect:/games/lobby/"+id);

        invitation.setGame(game);
        invitation.setPlayer1(player1);
        invitation.setPlayer2(player2);

        invitationService.save(invitation);

        return mav;
    }

     @GetMapping(path="/parchis")
    public ModelAndView parchis() {
        ModelAndView mav = new ModelAndView("game/parchisBoard");
        mav.addObject("board",new ParchisBoard());
      
        return mav;
    }

}
