package com.glownia.maciej.dogtrainingcourses;

import com.glownia.maciej.dogtrainingcourses.entity.Course;
import com.glownia.maciej.dogtrainingcourses.repository.CourseRepository;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = DogTrainingCoursesApplication.class)
class CourseRepositoryApplicationTest {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	CourseRepository repository;

	@Test
	void testFindById() {
		Course course = repository.findById(1L);
		Course course2 = repository.findById(2L);
		Course course3 = repository.findById(3L);

		assertEquals("5 basics commands to practice everyday.", course.getName());
		assertEquals("How to build relationship with your dog?", course2.getName());
		assertEquals("Everything about feeding.", course3.getName());

		logger.info("Test is running.");
	}
}