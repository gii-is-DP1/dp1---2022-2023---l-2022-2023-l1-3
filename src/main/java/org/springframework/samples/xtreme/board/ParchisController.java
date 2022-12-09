package org.springframework.samples.xtreme.board;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.spi.ObjectThreadContextMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(path="/parchis")
public class ParchisController {
    
    @Autowired
    ParchisBoardService parchisService;

    @GetMapping(path = "")
    public ModelAndView game(HttpServletResponse response) {
        response.addHeader("Refresh", "10");
        ModelAndView mav = new ModelAndView("game/parchisBoard");
        mav.addObject("board", parchisService.findById(1).get());
        return mav;
    }
}
