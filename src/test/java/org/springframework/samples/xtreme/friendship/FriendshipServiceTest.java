package org.springframework.samples.xtreme.friendship;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.xtreme.player.Player;
import org.springframework.samples.xtreme.player.PlayerService;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class FriendshipServiceTest {

    @Autowired
	protected FriendshipService friendshipService;
	@Autowired
    protected PlayerService playerService;


    @Test
    void testGetAll(){
        Collection<Friendship> friendships = this.friendshipService.getAll();
        assertThat(friendships.size()>0).isTrue();
    }

    @Test
    void testFriendshipByUsername(){
        Collection<Friendship> friendships = this.friendshipService.getFriendshipsByUsername("elon");
        assertThat(friendships.size()>0).isTrue();

        Boolean b= friendships.stream()
            .allMatch(x->x.getPlayer1().getUser().getUsername().equals("elon")|| 
                x.getPlayer2().getUser().getUsername().equals("elon"));
                
        assertTrue(b);
    }

    @Test
    void testdGetAcceptedFriendshipsByUsername(){
        Collection<Friendship> friendship = this.friendshipService.getAcceptedFriendshipsByUsername("elon");
        assertThat(friendship.size()>0).isTrue();
        friendship.forEach(f -> assertThat(f.getFriendshipState().equals(FriendshipState.ACCEPTED)).isTrue());
    }

    @Test
    void testdGetPendingdFriendshipsByUsername(){
        Collection<Friendship> friendship = this.friendshipService.getPendingFriendshipsByUsername("xavi");
        assertThat(friendship.size()>0).isTrue();
        friendship.forEach(f -> assertThat(f.getFriendshipState().equals(FriendshipState.PENDING)).isTrue());
    }


    @Test
    void testSaveFriendship(){
        Player player1 = playerService.findByUsername("xavi");
        Player player2 = playerService.findByUsername("elon");

        Friendship f = new Friendship();
        f.setFriendshipState(FriendshipState.ACCEPTED);
        f.setPlayer1(player1);
        f.setPlayer2(player2);
        friendshipService.saveFriendship(f);

        Collection<Friendship> friendships = this.friendshipService.getAll();

        Boolean b=friendships.stream().anyMatch(x->x.getPlayer1().getUser().getUsername().equals("xavi") && 
        x.getPlayer2().getUser().getUsername().equals("elon") && x.getFriendshipState().equals(FriendshipState.ACCEPTED));

        assertTrue(b);
    }

}
