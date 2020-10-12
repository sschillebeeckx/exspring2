package be.abis.exercise.controller;

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
	
	Person loggedInperson;
	List<Course> coursesFound;
	
	@GetMapping("/")
	public String login(Model model){
		model.addAttribute("login",new Login());
		return "login";
	}
	
	@PostMapping("/")
	public String welcome(Model model, Login login) {
		loggedInperson =trainingService.findPerson(login.getEmail(),login.getPassword());
		return "redirect:/welcome";
	}
	
	@GetMapping("/welcome")
	public String showWelcome(Model model){
		model.addAttribute("person", loggedInperson);
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
		System.out.println(coursesFound);
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
		model.addAttribute("person", loggedInperson);
		return "redirect:/welcome";
	}
	
}
