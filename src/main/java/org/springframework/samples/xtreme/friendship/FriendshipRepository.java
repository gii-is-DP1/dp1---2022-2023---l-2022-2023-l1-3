package org.springframework.samples.xtreme.friendship;

import org.springframework.stereotype.Repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


@Repository
public interface FriendshipRepository extends CrudRepository<Friendship,Integer>{
    
    @Query("SELECT f FROM Friendship f")
    public Collection<Friendship> findAll();

    @Query("SELECT f FROM Friendship f WHERE lower(f.player1.user.username) = lower(?1) or lower(f.player2.user.username) = lower(?1)")
    public Collection<Friendship> findAllByUsername(String username);

    @Query("SELECT f FROM Friendship f WHERE (lower(f.player1.user.username) = lower(?1) or lower(f.player2.user.username) = lower(?1)) and f.friendshipState = 'PENDING'")
    public Collection<Friendship> findAllPendingFriendshipByUsername(String username);

    @Query("SELECT f FROM Friendship f WHERE (lower(f.player1.user.username) = lower(?1) or lower(f.player2.user.username) = lower(?1)) and f.friendshipState = 'ACCEPTED'")
    public Collection<Friendship> findAllAcceptedFriendshipByUsername(String username);

}
