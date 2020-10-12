package be.abis.exercise.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import be.abis.exercise.model.Course;
import be.abis.exercise.model.Login;
import be.abis.exercise.model.Person;
import be.abis.exercise.service.TrainingService;

@Controller
public class AppController {
     
	@Autowired
	TrainingService trainingService;
	
	Person loggedInPerson;
	List<Course> coursesFound;
	List<Person> personsFound;
	Person removedPerson;
	Person addedPerson;
	
	@GetMapping("/")
	public String login(Model model){
		model.addAttribute("login",new Login());
		return "login";
	}
	
	@PostMapping("/")
	public String welcome(Model model, Login login) {
		loggedInPerson =trainingService.findPerson(login.getEmail(),login.getPassword());
		return "redirect:/welcome";
	}
	
	@GetMapping("/welcome")
	public String showWelcome(Model model){
		model.addAttribute("person", loggedInPerson);
		return "welcome";
	}
	
	@GetMapping("/logout")
	public String logout(){
		return "redirect:/";
	}	
	
	@GetMapping("/coursesearch")
	public String searchCourses(Model model){
		model.addAttribute("course1",new Course());
		model.addAttribute("course2",new Course());
		return "coursesearch";
	}	
	
	@GetMapping("/courseList")
	public String showAllCourses(){
		coursesFound = trainingService.getCourseService().findAllCourses();		
		return "redirect:/showcourses";
	}
	
	@GetMapping("/showcourses")
	public String showCourses(Model model){
		model.addAttribute("courses", coursesFound);
		return "showcourses";
	}
	
	
	@GetMapping("/backToCourseSearch")
	public String backToCourseSearch(){
		return "redirect:/coursesearch";
	}	
	
	@PostMapping("/findCourseById")
	public String findCourseById(Course course1){
		int id = Integer.parseInt(course1.getCourseId());
		Course courseFound = trainingService.getCourseService().findCourse(id);	
		coursesFound = new ArrayList<Course>();
		coursesFound.add(courseFound);
		return "redirect:/showcourses";
	}
	
	@PostMapping("/findCourseByTitle")
	public String findCourseByTitle(Course course2){
		Course courseFound = trainingService.getCourseService().findCourse(course2.getShortTitle());	
		coursesFound = new ArrayList<Course>();
		coursesFound.add(courseFound);
		return "redirect:/showcourses";
	}
	
	@GetMapping("/backToWelcome")
	public String backToWelcome(Model model){
		model.addAttribute("person", loggedInPerson);
		return "redirect:/welcome";
	}
	
	@GetMapping("/personadmin")
	public String gotopersonadmin(Model model){
		return "personadmin";
	}
	
	@GetMapping("/changepwd")
	public String showChangePwd(Model model) {
		model.addAttribute("person",loggedInPerson);
		return "changepwd";
	}
	
	@PostMapping("/changepwd")
	public String postNewPassword(Model model, Person person) {
		try {
			trainingService.changePassword(loggedInPerson, person.getPassword());			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/confirmpwdchanged";
		
	}
	
	@GetMapping("/confirmpwdchanged")
	public String showPwdChanged(Model model) {
		model.addAttribute("person", loggedInPerson);
		return "confirmpwdchanged";
	}
	
	@GetMapping("/personsearch")
	public String searchPersons(Model model){
		model.addAttribute("person1",new Person());
		return "personsearch";
	}	
	
	@GetMapping("/personList")
	public String showAllPersons(){
		personsFound = trainingService.getAllPersons();		
		return "redirect:/showpersons";
	}
	
	@GetMapping("/showpersons")
	public String showPersons(Model model){
		model.addAttribute("persons", personsFound);
		return "showpersons";
	}
	
	@GetMapping("/backToPersonSearch")
	public String backToPersonSearch(){
		return "redirect:/personsearch";
	}
	
	@PostMapping("/findPersonById")
	public String findPersonById(Person person1){
		Person personFound = trainingService.findPerson(person1.getPersonId());	
		personsFound = new ArrayList<Person>();
		personsFound.add(personFound);
		return "redirect:/showpersons";
	}
	
	@GetMapping("/removeperson")
	public String removePerson(Model model){
		model.addAttribute("person", new Person());
		return "removeperson";
	}
	
	@PostMapping("/removePersonById")
	public String removePersonById(Person person){
		removedPerson=trainingService.findPerson(person.getPersonId());
		trainingService.deletePerson(person.getPersonId());	
		return "redirect:/confirmpersonremoved";
	}
	
	@GetMapping("/confirmpersonremoved")
	public String showPersonRemoved(Model model) {
		model.addAttribute("person",removedPerson);
		return "confirmpersonremoved";
	}
	
	@GetMapping("/addperson")
	public String addPerson(Model model){
		model.addAttribute("person", new Person());
		return "addperson";
	}
	
	@PostMapping("/addPerson")
	public String addPerson(Person person){
		try {
			trainingService.addPerson(person);
			addedPerson = trainingService.findPerson(person.getPersonId());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return "redirect:/confirmpersonadded";
	}
	
	@GetMapping("/confirmpersonadded")
	public String showPersonAdded(Model model) {
		model.addAttribute("person",addedPerson);
		return "confirmpersonadded";
	}
}
