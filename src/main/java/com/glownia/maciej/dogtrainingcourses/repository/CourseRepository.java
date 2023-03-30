package com.glownia.maciej.dogtrainingcourses.repository;

import com.glownia.maciej.dogtrainingcourses.entity.Course;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Repository
@Transactional // make sure you imported this: import org.springframework.transaction.annotation.Transactional;
public class CourseRepository {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EntityManager entityManager;

    public Course findById(Long id) {
        return entityManager.find(Course.class, id);
    }

    public void deleteById(Long id) {
        Course course = findById(id);
        entityManager.remove(course);
    }

    public void save(Course course) {
        if(course.getId() == null) {
            entityManager.persist(course); // we save it
        } else {
            entityManager.merge(course); // we update it
        }
    }

    /**
     * NOTE: When play with this check results in H2-console
     */
    public void exploreEntityManagerByPlayingAroundIt() {

        logger.info("exploreEntityManagerByPlayingAroundIt() has been run");

        Course course1 = new Course("4 paws in the forest.");
        entityManager.persist(course1); // takes an entity instance, adds it to the context and makes that instance managed (i.e. future updates to the entity will be tracked).

        Course course2 = new Course("Our dog in public transport.");
        entityManager.persist(course2);

        entityManager.flush(); // will force the data to be persisted in the database immediately

        course1.setName("4 paws in the forest - version 2.");
        course2.setName("Our dog in public transport - version 2.");

        entityManager.refresh(course1); // overrides the changes and refresh the content from database

        entityManager.flush();
    }
}
