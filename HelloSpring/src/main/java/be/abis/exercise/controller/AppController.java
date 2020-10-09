package be.abis.exercise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import be.abis.exercise.model.Person;
import be.abis.exercise.service.PersonService;

@Controller
public class AppController {
	
	@Autowired
	PersonService personService;
	
	@GetMapping("/")
	public String showBlabla(Model model) {
		Person p = personService.findPersonById(3);
		model.addAttribute("person",p);
		return "blabla";
	}
	

}
