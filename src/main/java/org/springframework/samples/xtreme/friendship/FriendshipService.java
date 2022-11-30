package org.springframework.samples.xtreme.friendship;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.xtreme.player.Player;
import org.springframework.samples.xtreme.player.PlayerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FriendshipService {

    private FriendshipRepository friendshipRepository;
    private PlayerRepository playerRepository;

    @Autowired
    public FriendshipService(FriendshipRepository friendshipRepository, PlayerRepository playerRepository) {
        this.friendshipRepository = friendshipRepository;
        this.playerRepository = playerRepository;
    }

    @Transactional(readOnly = true)
    public Collection<Friendship> getAll(){
        return friendshipRepository.findALl();
    }

    @Transactional(readOnly = true)
    public Collection<Friendship> getFriendshipsByUsername(String username) throws DataAccessException{
        return friendshipRepository.findAllByUsername(username);
    }

    @Transactional(readOnly = true)
    public Collection<Friendship> getAcceptedFriendshipsByUsername(String username){
        return friendshipRepository.findAllAcceptedFriendshipByUsername(username);
    }

    @Transactional(readOnly = true)
    public Collection<Friendship> getPendingFriendshipsByUsername(String username){
        return friendshipRepository.findAllPendingFriendshipByUsername(username);
    }

    @Transactional
    public Friendship saveFriendship(Friendship friendship){
        return friendshipRepository.save(friendship);
    }

    @Transactional
    public Friendship saveFriendshipAccepted(Friendship friendship){
        friendship.setFriendshipState(FriendshipState.ACCEPTED);
        return friendshipRepository.save(friendship);
    }

    @Transactional
    public Friendship saveFriendshipPending(Friendship friendship){
        friendship.setFriendshipState(FriendshipState.PENDING);
        return friendshipRepository.save(friendship);
    }

    @Transactional
    public Friendship saveFriendshipCancelled(Friendship friendship){
        friendship.setFriendshipState(FriendshipState.CANCELLED);
        return friendshipRepository.save(friendship);
    }
}
