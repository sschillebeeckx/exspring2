package be.abis.exercise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import be.abis.exercise.repository.PersonRepository;
import be.abis.exercise.service.AbisTrainingService;
import be.abis.exercise.service.CourseService;
import be.abis.exercise.service.TrainingService;

@SpringBootApplication
public class ExA4Application {
	
	//@Autowired ApplicationContext context;
	
	public static void main(String[] args) {
		SpringApplication.run(ExA4Application.class, args);
	}
	
	/*@Bean
	public TrainingService abisTrainingService(PersonRepository personRepository) {
		TrainingService ts = new AbisTrainingService(personRepository);
		
		CourseService courseService = (CourseService)context.getBean(CourseService.class);
		ts.setCourseService(courseService);
		return ts;
	}*/

}
