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
	    return "welcome";
	  }
}
