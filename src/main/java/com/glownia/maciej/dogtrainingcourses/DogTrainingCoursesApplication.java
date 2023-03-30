package com.glownia.maciej.dogtrainingcourses;

import com.glownia.maciej.dogtrainingcourses.entity.Course;
import com.glownia.maciej.dogtrainingcourses.repository.CourseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DogTrainingCoursesApplication implements CommandLineRunner {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CourseRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(DogTrainingCoursesApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Course course = repository.findById(1L);

		logger.info("Course 1 -> {}", course);

//		repository.deleteById(1L); // when delete 1 course testSave() will fail, so we need to comment this line
	}
}
