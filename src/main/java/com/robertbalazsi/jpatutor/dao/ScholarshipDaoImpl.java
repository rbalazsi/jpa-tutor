package com.robertbalazsi.jpatutor.dao;

import com.robertbalazsi.jpatutor.entity.Scholarship;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by robi on 7/24/16.
 */
@Repository
public class ScholarshipDaoImpl implements ScholarshipDao {

    @PersistenceContext
    private EntityManager em;

    public void store(Scholarship scholarship) {
        if (scholarship.getId() == null) {
            em.persist(scholarship);
        } else {
            em.merge(scholarship);
        }
        em.flush();
    }

    public void remove(Scholarship scholarship) {
        em.remove(scholarship);
        em.flush();
    }
}
