package org.springframework.samples.xtreme.web;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.xtreme.model.Person;
import org.springframework.samples.xtreme.player.Player;
import org.springframework.samples.xtreme.player.PlayerService;
import org.springframework.samples.xtreme.util.UserUtils;
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

	private UserUtils userUtils = new UserUtils();
    
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

		UserDetails currentUser = userUtils.getUserDetails();
		Boolean isAdmin = userUtils.isAdmin(currentUser);

		if (!isAdmin) {
			Player actualPlayer= this.playerService.findByUsername(currentUser.getUsername());
            actualPlayer.setIsOnline(true);
            this.playerService.save(actualPlayer);
		}

        mav.addObject("isAdmin", isAdmin);
        mav.addObject("username", currentUser.getUsername());
        return mav;
    }

}
