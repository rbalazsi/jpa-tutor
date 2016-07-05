package com.robertbalazsi.jpatutor.dao;

import com.robertbalazsi.jpatutor.entity.Course;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by robi on 7/5/16.
 */
@Repository
public class CourseDaoImpl implements CourseDao {

    @PersistenceContext
    private EntityManager em;

    public void store(Course course) {
        if (course.getId() == null) {
            em.persist(course);
        } else {
            em.merge(course);
        }
        em.flush();
    }
}
