package com.glownia.maciej.dogtrainingcourses.repository;

import com.glownia.maciej.dogtrainingcourses.entity.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class CourseRepository {

    @Autowired
    EntityManager entityManager;

    public Course findById(Long id) {
        return entityManager.find(Course.class, id);
    }
}
