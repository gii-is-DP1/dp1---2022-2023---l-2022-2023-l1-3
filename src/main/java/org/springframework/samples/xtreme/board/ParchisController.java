package org.springframework.samples.xtreme.board;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.spi.ObjectThreadContextMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(path="/parchis")
public class ParchisController {

    private static final String GAME_VIEW = "parchis/parchisView";
    
    @Autowired
    ParchisBoardService parchisService;

    @GetMapping(path = "")
    public ModelAndView gameTest(HttpServletResponse response) {
        response.addHeader("Refresh", "10");
        ModelAndView mav = new ModelAndView("game/parchisBoard");
        mav.addObject("board", parchisService.findById(1).get());
        return mav;
    }

    @GetMapping(path = "/{id}")
    public ModelAndView gameRoom(@PathVariable Integer id, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView(GAME_VIEW);
        return mav;
    }

    @GetMapping(path = "/{id}/turn")
    public ModelAndView turnView(@PathVariable Integer id) {
        // TODO: Vista en la que el jugador lanza dados y elige ficha
        return null;
    }

}
