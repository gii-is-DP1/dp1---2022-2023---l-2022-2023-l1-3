package org.springframework.samples.xtreme.invitation;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvitationRepository extends CrudRepository<Invitation, Integer>{

    @Query("SELECT i FROM Invitation i")
    public Collection<Invitation> findAll();

    @Query("SELECT i FROM Invitation i WHERE lower(i.player1.user.username) = lower(?1) or lower(i.player2.user.username) = lower(?1)")
    public Collection<Invitation> findAllByUsername(String username);

    @Query("SELECT i FROM Invitation i WHERE lower(i.player2.user.username) = lower(?1)")
    public Collection<Invitation> findRecievedInvitationsByUsername(String username);

    @Query("SELECT i FROM Invitation i WHERE lower(i.player2.id) = lower(?1) and lower(i.game.id) = lower(?2)")
    public Invitation findInvitationPlayerToGame(Integer playerId,Integer gameId);

    @Query("SELECT i FROM Invitation i WHERE lower(i.player1.id) = lower(?1) and lower(i.player2.id) = lower(?2) and lower(i.game.id) = lower(?3)")
    public Invitation findInvitationToGameByPlayers(Integer player1Id,Integer player2Id,Integer gameId);
}
