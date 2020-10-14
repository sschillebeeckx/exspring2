package be.abis.exercise;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import be.abis.exercise.repository.PersonRepository;
import be.abis.exercise.service.AbisTrainingService;
import be.abis.exercise.service.CourseService;
import be.abis.exercise.service.TrainingService;

@SpringBootApplication
public class ExD1Application implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(ExD1Application.class, args);
	}	
	
	@Override 
	public void addViewControllers(ViewControllerRegistry registry) {
	    registry.addViewController("/").setViewName("login.html");
	}
	
	@Bean
	@Profile("production")
	public DataSource testDataSource(){
		return new EmbeddedDatabaseBuilder()
					.setType(EmbeddedDatabaseType.DERBY)
					.addScript("classpath:create_course_table_derby.sql")
					.build();
	}
	
	@Bean
	@Profile("production")
	public JdbcTemplate jdbcTemplateDerby(){
		return new JdbcTemplate(testDataSource());
	}
		

}
