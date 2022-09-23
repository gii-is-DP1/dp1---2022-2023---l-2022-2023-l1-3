package org.springframework.samples.petclinic.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.samples.petclinic.model.Person;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {
	
	
	  @GetMapping({"/","/welcome"})
	  public String welcome(Map<String, Object> model) {	    
		List<Person> persons = new ArrayList<Person>();
		Person p1 = new Person();
		p1.setFirstName("Alejandro");
		p1.setLastName("Medina");
		persons.add(p1);
		model.put("persons",persons);
		model.put("title","Practica 1");
		model.put("group","estudiante");
	    return "welcome";
	  }
}
