package com.glownia.maciej.dogtrainingcourses.repository;

import com.glownia.maciej.dogtrainingcourses.entity.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Repository
@Transactional // make sure you imported this: import org.springframework.transaction.annotation.Transactional;
public class CourseRepository {

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
}
