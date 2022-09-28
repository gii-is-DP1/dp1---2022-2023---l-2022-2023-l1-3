package org.springframework.samples.petclinic.web;

import java.util.*;

import org.springframework.samples.petclinic.model.Person;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {
	
	
	  @GetMapping({"/","/welcome"})
	  public String welcome(Map<String, Object> model) {	 
		List<Person> persons =  new ArrayList<Person>();
		Person persona = new Person();
		persona.setFirstName("Alejandro");
		persona.setLastName("Gallardo");
		persons.add(persona);
		model.put("persons",persons);
		model.put("title","My project");
		model.put("group","Teachers");
		


	    return "welcome";
	  }
}
