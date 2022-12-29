package org.springframework.samples.xtreme.web;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.xtreme.model.Person;
import org.springframework.samples.xtreme.player.Player;
import org.springframework.samples.xtreme.player.PlayerService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WelcomeController {
	
	private static final String WELCOME = "welcome";
	private static final String HOME = "home";

	private final PlayerService playerService;
    
    @Autowired
    public WelcomeController(PlayerService playerService){
        this.playerService=playerService;
    }

	@GetMapping({"/","/welcome"})
	public String welcome(Map<String, Object> model) {	    
		List<Person> persons = new ArrayList<Person>();
    
		Person p1 = new Person();
		p1.setFirstName("Alejandro");
		p1.setLastName(" Medina Duran");

		Person p2 = new Person();
		p2.setFirstName("Alberto Miguel");
		p2.setLastName(" Lopez-Benjumea Novella");

		Person p3 = new Person();
		p3.setFirstName("Alejandro");
		p3.setLastName(" Gallardo Pelayo");

		Person p4 = new Person();
		p4.setFirstName("Gonzalo");
		p4.setLastName(" Campos Mejías");

		Person p5 = new Person();
		p5.setFirstName("Manuel");
		p5.setLastName(" Borrego Reina");

		persons.add(p1);
		persons.add(p2);
		persons.add(p3);
		persons.add(p4);
		persons.add(p5);

		model.put("persons",persons);
		model.put("title","Xtreme Parchis&Oca");
		model.put("group","L1-3");
	    return WELCOME;
	}

	@GetMapping(path="/home")
    public ModelAndView gameHome() {
        ModelAndView mav = new ModelAndView(HOME);

        // obtener el usuario actualmente logueado
        Object principal=SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails=null;
        Boolean esAdmin = false;
        String user = "";
        if (principal instanceof UserDetails) {

            userDetails = (UserDetails) principal;
            System.out.println("---Nombre actual del usuario logueado: "+userDetails.getUsername());
            System.out.println("su rol es: "+ userDetails.getAuthorities());
            esAdmin=userDetails.getAuthorities().stream().anyMatch(x-> x.getAuthority().equals("admin"));
            user=userDetails.getUsername();
            if(!esAdmin){
            	Player actualPlayer= this.playerService.findByUsername(user);
            	actualPlayer.setIsOnline(true);
            	this.playerService.save(actualPlayer);
            }
        }

        mav.addObject("esAdmin", esAdmin);
        mav.addObject("user", user);
        return mav;
    }

}
