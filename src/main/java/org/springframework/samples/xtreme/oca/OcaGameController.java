package org.springframework.samples.xtreme.oca;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.xtreme.board.OcaBoard;
import org.springframework.samples.xtreme.game.GameService;
import org.springframework.samples.xtreme.game.GameState;
import org.springframework.samples.xtreme.board.OcaBoardService;
import org.springframework.samples.xtreme.game.Game;
import org.springframework.samples.xtreme.oca.OcaTurn;
import org.springframework.samples.xtreme.oca.OcaPiece;
import org.springframework.samples.xtreme.oca.OcaPieceService;
import org.springframework.samples.xtreme.player.Player;
import org.springframework.samples.xtreme.player.PlayerService;
import org.springframework.samples.xtreme.user.User;
import org.springframework.samples.xtreme.util.UserUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/ocaGames")
public class OcaGameController {
	
	private static final String VIEWS_GAME_CREATE_OR_UPDATE_FORM = "ocaGames/editGame";

    private UserUtils userUtils = new UserUtils();
	
	private GameService ocaGameService;
	
	private OcaBoardService ocaBoardService;
	
	private PlayerService playerService;
	
	private OcaTurnService ocaTurnService;
	
	private OcaPieceService ocaPieceService;
	
	
	@Autowired
	public OcaGameController(GameService ocaGameService, OcaBoardService ocaBoardService,
			PlayerService playerService, OcaTurnService ocaTurnService, OcaPieceService ocaPieceService) {
		super();
		this.ocaGameService = ocaGameService;
		this.ocaBoardService = ocaBoardService;
		this.playerService = playerService;
		this.ocaTurnService = ocaTurnService;
		this.ocaPieceService = ocaPieceService;
	}
	
	@GetMapping(path="/winner/{ocaGameId}/{username}")
	public ModelAndView showWinner(@PathVariable("username") String username,@PathVariable("ocaGameId") int ocaGameId) {
		ModelAndView mav = new ModelAndView("ocaGames/winner");
		Player player = this.playerService.findByUsername(username);
		Game ocaGame = this.ocaGameService.findGameById(ocaGameId).get();
		ocaGame.setPlayerWinner(player);
        ocaGame.setStateGame(GameState.FINISHED);
        ocaGameService.save(ocaGame);
		mav.addObject(ocaGame);
		mav.addObject(player);
		return mav;
	}


	@GetMapping("/{ocaGameId}")
	public ModelAndView showGame(@PathVariable("ocaGameId") int ocaGameId, HttpServletResponse response,@AuthenticationPrincipal Authentication user) {
		response.addHeader("Refresh", "2");
		ModelAndView mav = new ModelAndView("ocaGames/ocaGameDetails");
		OcaTurn turn = this.ocaTurnService.findTurnById(ocaGameId);
		OcaPiece piece = new OcaPiece();
        UserDetails currentUser = userUtils.getUserDetails();
        Player currentPlayer = playerService.findByUsername(currentUser.getUsername());
		List<Player> players = turn.getPlayers();
		if(!(players.contains(currentPlayer))) {
			players.add(currentPlayer);
			if(currentPlayer==turn.getPlayers().get(0)) {
				turn.setPlayer(currentPlayer);
				this.ocaTurnService.save(turn);
			}
			piece.setPlayer(currentPlayer);
			this.ocaPieceService.save(piece);
		}
		piece.setPlayer(currentPlayer);
		this.ocaPieceService.save(piece);
		OcaPiece piece_2=this.ocaPieceService.findByPlayerId(currentPlayer.getId());
		if(piece_2!=null) {
			piece = piece_2;
		}
		mav.addObject(piece);
		mav.addObject(currentPlayer);
		mav.addObject(turn);
		mav.addObject(this.ocaGameService.findGameById(ocaGameId));
		mav.addObject(this.ocaBoardService.findById(ocaGameId));
		return mav;
	}

}
