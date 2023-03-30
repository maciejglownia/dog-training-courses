package com.glownia.maciej.dogtrainingcourses;

import com.glownia.maciej.dogtrainingcourses.entity.Course;
import com.glownia.maciej.dogtrainingcourses.repository.CourseRepository;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

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

    @Test
    @DirtiesContext// after test is run Spring automatically reset the data -> we want to have state as it was so other tests not fail
    void testDeleteById() {
        repository.deleteById(2L);
        assertNull(repository.findById(2L));
    }

    /**
     * Assumptions:
     * 1. Get a course and check if the name is correct.
     * 2. Set a new name for the course.
     * 3. Check if value has been changed is correct.
     * NOTE: Make sure in DogTrainingCoursesApplication you're not doing any manipulation of data in database
     * which can causes an error
     */
    @Test
    @DirtiesContext
    void testSave() {
        Course course = repository.findById(1L);
        assertEquals("5 basics commands to practice everyday.", course.getName());

        course.setName("5 basics commands to practice everyday - version 2.");
        repository.save(course);

        Course course1 = repository.findById(1L);
        assertEquals("5 basics commands to practice everyday - version 2.", course1.getName());
    }

    @Test
    @DirtiesContext
    void testEntityManager() {
        repository.exploreEntityManagerByPlayingAroundIt();
    }
}