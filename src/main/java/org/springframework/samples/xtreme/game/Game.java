package org.springframework.samples.xtreme.game;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.samples.xtreme.admin.Admin;
import org.springframework.samples.xtreme.board.OcaBoard;
import org.springframework.samples.xtreme.board.ParchisBoard;
import org.springframework.samples.xtreme.chat.Chat;
import org.springframework.samples.xtreme.model.BaseEntity;
import org.springframework.samples.xtreme.player.Player;

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

    @NotNull
    private String gameName;

    @Column(name="name_game")
    @Enumerated(EnumType.STRING)
    @NotNull
    private TypeGame typeGame;

    
    @JoinColumn(name="name_creator_player")
    //@NotNull
    private String creatorPlayer;

 
    @Column(name = "isPublic")
    //@NotNull
    private Boolean isPublic = false;

    @ManyToMany(mappedBy = "games")
    private List<Admin> administrators;

    @ManyToMany(mappedBy = "games")
    private List<Player> players;

    @OneToOne(cascade= CascadeType.ALL)
    @JoinColumn(name="chat_id")
    private Chat chat;


}
