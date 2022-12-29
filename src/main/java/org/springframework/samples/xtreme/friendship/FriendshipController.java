package org.springframework.samples.xtreme.friendship;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Comparator;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.xtreme.player.Player;
import org.springframework.samples.xtreme.player.PlayerService;
import org.springframework.samples.xtreme.util.UserUtils;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.security.core.userdetails.UserDetails;

@Controller
@RequestMapping(path="/friends")
public class FriendshipController {

    private static final String FRIENDS = "friendship/friends";
    private static final String CREATE_FRIENDSHIP = "friendship/createFriend";
    private static final String CREATE_FRIENDSHIP_ACCEPTED = "friendship/createFriendshipAccepted";
    private static final String LIST_PLAYERS = "friendship/listNewFriends";
    private static final String ALL_MY_FRIENDSHIPS = "friendship/friendshipsPending";

    private final PlayerService playerService;
    private final FriendshipService friendshipService;

    private UserUtils userUtils = new UserUtils();


    @Autowired
    public FriendshipController(PlayerService playerService,FriendshipService friendshipService) {
        this.playerService = playerService;
        this.friendshipService=friendshipService;
    }

    @GetMapping
    public ModelAndView friends(){
        ModelAndView mav= new ModelAndView(FRIENDS);

        UserDetails currentUser = userUtils.getUserDetails();

        Player p = playerService.findByUsername(currentUser.getUsername());
      
        Friendship fs= new Friendship();
        fs.setPlayer1(p);
        fs.setFriendshipState(FriendshipState.PENDING);
        mav.addObject("friendship", fs);

        Collection<Friendship> col = friendshipService.getAcceptedFriendshipsByUsername(currentUser.getUsername());
        List<Player> friends = col.stream().filter(x->x.getPlayer1().equals(p)).map(x->x.getPlayer2()).collect(Collectors.toList());    
        List<Player> friends2 = col.stream().filter(x->x.getPlayer2().equals(p)).map(x->x.getPlayer1()).collect(Collectors.toList());    
        friends.addAll(friends2);
        friends.sort(Comparator.comparing(Player::getIsOnline).reversed());
        mav.addObject("myfriends", friends);

        Collection<Friendship> fs_Pending = friendshipService.getPendingFriendshipsByUsername(currentUser.getUsername());
        List<Player> friendsPending = fs_Pending.stream().filter(x->x.getPlayer1().equals(p)).map(x->x.getPlayer2()).collect(Collectors.toList());    
        mav.addObject("myfriendsPending", friendsPending);

        return mav;
    }



    @GetMapping(path="/sendFriendship")
    public ModelAndView viewPlayers(){
        ModelAndView mav = new ModelAndView(LIST_PLAYERS);

        UserDetails currentUser = userUtils.getUserDetails();

        Player p = playerService.findByUsername(currentUser.getUsername());

        List<Player> allPlayers=  playerService.getAllPlayers();

        Collection<Friendship> myFriends= friendshipService.getFriendshipsByUsername(currentUser.getUsername());
        List<Player> friends1 = myFriends.stream().filter(x->x.getPlayer1().equals(p)).map(x->x.getPlayer2()).collect(Collectors.toList());    
        List<Player> friends2 = myFriends.stream().filter(x->x.getPlayer2().equals(p)).map(x->x.getPlayer1()).collect(Collectors.toList());    
        friends1.addAll(friends2);
                    
        allPlayers.removeAll(friends1);
        allPlayers.remove(p);
        mav.addObject("players", allPlayers);

        return mav;
    }


    
    @GetMapping(path="/pendingFriendships")
    public ModelAndView pendingFriendship(){
        ModelAndView mav = new ModelAndView(ALL_MY_FRIENDSHIPS);

        UserDetails currentUser = userUtils.getUserDetails();

        Player p = playerService.findByUsername(currentUser.getUsername());

        Collection<Friendship> fs_Pending = friendshipService.getPendingFriendshipsByUsername(currentUser.getUsername());
        List<Player> friendsPending = fs_Pending.stream().filter(x->x.getPlayer2().equals(p)).map(x->x.getPlayer1()).collect(Collectors.toList());    
        mav.addObject("friendsPending", friendsPending);
               
        return mav;
    }



    @GetMapping(path = "/pendingFriendships/{username}")
    public ModelAndView createFriendshipAccepted(@PathVariable String username){
        ModelAndView mav= new ModelAndView(CREATE_FRIENDSHIP_ACCEPTED);
        Player player = playerService.findByUsername(username);
        mav.addObject("player", player);

        return mav;
    }

    @PostMapping(path="/pendingFriendships/{username}")
    public ModelAndView createFriendshipAcceptedPost(@Valid @ModelAttribute("friendship") Friendship friendship, BindingResult res, @RequestParam String state,@PathVariable("username") String username){
        ModelAndView mav = new ModelAndView("redirect:/friends");

        UserDetails currentUser = userUtils.getUserDetails();

        Player player2 = playerService.findByUsername(currentUser.getUsername());

        Player player1 = this.playerService.findByUsername(username);

        Collection<Friendship> fs= friendshipService.getPendingFriendshipsByUsername(currentUser.getUsername());
        Friendship fs_pending= fs.stream().filter(x->x.getPlayer1().equals(player1) && x.getPlayer2().equals(player2))
                                    .findFirst().get();

        if(state.equals("aceptado")){
            this.friendshipService.saveFriendshipAccepted(fs_pending);
        }else{
            this.friendshipService.saveFriendshipCancelled(fs_pending);
        }

        return mav;
    }




    @GetMapping(path = "/{username}")
    public ModelAndView createFriendshipPending(@PathVariable String username){
       ModelAndView mav= new ModelAndView(CREATE_FRIENDSHIP);
       Player player = playerService.findByUsername(username);
       
       UserDetails currentUser = userUtils.getUserDetails();


       if (currentUser!= null) {
            mav.addObject("player", player);
            mav.addObject("frienship", new Friendship()); 
       }

        return mav;
    }

    @PostMapping(path="/{username}")
    public ModelAndView createFriendshipPendingPost(@Valid @ModelAttribute("frindship") Friendship friendship, BindingResult res, @PathVariable("username") String username){
        ModelAndView mav = new ModelAndView("redirect:/friends");

        UserDetails currentUser = userUtils.getUserDetails();


        Player player1 = playerService.findByUsername(currentUser.getUsername());
        Player player2 = this.playerService.findByUsername(username);


        if(player1 != player2) {
            friendship.setPlayer1(player1);
            friendship.setPlayer2(player2);

            Collection<Friendship> amigos = friendshipService.getFriendshipsByUsername(player1.getUser().getUsername());
            Boolean existFrienship= amigos.stream().anyMatch(x->x.getPlayer1().equals(player2) || x.getPlayer2().equals(player2));
            
            if(!existFrienship) {
                friendshipService.saveFriendship(friendship);
            }
        }
        
        return mav;
    }
    
}
