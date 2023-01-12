package org.springframework.samples.xtreme.game;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.samples.xtreme.board.ParchisBoard;
import org.springframework.samples.xtreme.chat.Chat;
import org.springframework.samples.xtreme.model.BaseEntity;
import org.springframework.samples.xtreme.pieces.ParchisPiece;
import org.springframework.samples.xtreme.player.Player;
import java.util.Random;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "game")
public class Game extends BaseEntity {

    @Column(name="num_players")
    //@NotNull
    @Min(2)
    @Max(4)
    private Integer numPlayers;

    @NotEmpty
    private String gameName;

    @Column(name="game_type")
    @Enumerated(EnumType.STRING)
    @NotNull
    private GameType gameType;

    @OneToOne
    @JoinColumn(name="creator_player")
    private Player creatorPlayer;
 
    @Column(name = "isPublic")
    //@NotNull
    private Boolean isPublic = false;

    @OneToOne(cascade= CascadeType.ALL)
    @JoinColumn(name="chat_id")
    private Chat chat;

    @Column(name="game_state")
    @Enumerated(EnumType.STRING)
    @NotNull
    private GameState stateGame= GameState.WAITING_PLAYERS;

    @OneToOne(cascade= CascadeType.ALL)
    @JoinColumn(name="player_winner")
    private Player playerWinner;


    @JoinTable(
        name = "rel_games_players",
        joinColumns = @JoinColumn(name = "game_id", nullable = false),
        inverseJoinColumns = @JoinColumn(name="player_id", nullable = false)
    )
    @ManyToMany
    private List<Player> players;

    public void addPlayerToGame(Player player){
        if(this.players == null){
            this.players = new ArrayList<>();
        }
        
        this.players.add(player);
    }

    public void removePlayerToGame(Player player){
        if(this.players != null){
            this.players.remove(player);
        }
    }


    private Integer i=0;//puntero
    private Integer dice;
    public  void TurnInit(){
        this.i=0;
        this.dice=null;
    }

    public void throwDice() {
        Random r = new Random();
        this.dice=r.nextInt(6)+1;// Entre 0 y 5, más 1

    }
    public void nextTurn() {
        if(this.i+1==this.players.size()) {
        this.i=0;
        }
        else {
            this.i++;
        }
    }
    public Player actualTurn(){
        return this.players.get(this.i);
    }
    

    @OneToOne(cascade= CascadeType.ALL)
    @JoinColumn(name="parchis_board")
    private ParchisBoard parchisBoard;


}
