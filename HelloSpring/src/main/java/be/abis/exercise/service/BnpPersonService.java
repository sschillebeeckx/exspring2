package be.abis.exercise.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import be.abis.exercise.model.Person;

//@Service
@Profile("production")
public class BnpPersonService implements PersonService {

	@Override
	public Person findPersonById(int id) {
		// TODO Auto-generated method stub
		return new Person("Kristof","Dhondt");
	}

}
