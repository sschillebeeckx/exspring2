package be.abis.exercise.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import be.abis.exercise.model.Person;

@Service
@Profile("test")
public class AbisPersonService implements PersonService {

	@Override
	public Person findPersonById(int id) {
		return new Person("Sandy","Schillebeeckx");
	}

}
