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

    //1. asignar colores a los jugadores
    //2. inicializar tablero con fichas en casa
    //3. quien tira primero (random)
    //4. Tira dado y si saca 5, automaticamente la ficha pasa de casa a casillaInicial
    //5. Moverse en el tablero
    //6. Casillas en las que no se puede comer / casilla puente
    //7. Si te chocas con un puente te deja en la casilla anterior al puente
    //8. Si llegas al final, la ficha se queda en la casilla final y no se puede mover
}
