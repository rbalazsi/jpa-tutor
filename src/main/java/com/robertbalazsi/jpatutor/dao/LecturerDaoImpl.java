package com.robertbalazsi.jpatutor.dao;

import com.robertbalazsi.jpatutor.entity.Lecturer;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by robi on 7/5/16.
 */
@Repository
public class LecturerDaoImpl implements LecturerDao {
    
    @PersistenceContext
    private EntityManager em;

    public void store(Lecturer lecturer) {
        if (lecturer.getId() == null) {
            em.persist(lecturer);
        } else {
            em.merge(lecturer);
        }
        em.flush();
    }
}
