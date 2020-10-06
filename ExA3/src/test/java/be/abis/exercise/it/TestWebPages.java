package be.abis.exercise.it;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.mockito.ArgumentMatchers.anyInt;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import be.abis.exercise.controller.CourseController;
import be.abis.exercise.model.Course;
import be.abis.exercise.service.CourseService;

@RunWith(SpringRunner.class)
@WebMvcTest(CourseController.class)
public class TestWebPages {
	
	@Autowired private MockMvc mockMvc;
	@MockBean CourseService courseService;
	
	@Test
	public void returnedPageContainsCorrectTitle() throws Exception {
		when(courseService.findCourse(anyInt())).thenReturn(new Course("7900","Maven","Mvaen",3,500.0));
		mockMvc.perform(get("/"))
		       .andDo(print())
		       .andExpect(status().isOk())
		       .andExpect(content().string(containsString("Maven")));
	}

}
