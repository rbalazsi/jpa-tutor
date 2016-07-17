package com.robertbalazsi.jpatutor.dao;

import com.robertbalazsi.jpatutor.entity.Lecturer;
import com.robertbalazsi.jpatutor.entity.Student;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class StudentDaoImpl implements StudentDao {

    private static final int BATCH_SIZE = 200;

    @PersistenceContext
    private EntityManager em;

    public List<Student> getAllStudents() {
        return em.createNamedQuery(Student.GET_ALL, Student.class)
                .getResultList();
    }

    public List<Student> getSubset(long minId) {
        return em.createNamedQuery(Student.GET_SUBSET, Student.class)
                .setParameter("minId", minId)
                .setMaxResults(BATCH_SIZE)
                .getResultList();
    }

    public List<Student> getByName(String name) {
        return em.createNamedQuery(Student.GET_BY_NAME, Student.class)
                .setParameter("name", name)
                .getResultList();
    }

    public List<Student> getStudentsLecturedBy(Lecturer lecturer) {
        return em.createNamedQuery(Student.GET_BY_LECTURER, Student.class)
                .setParameter("lectId", lecturer.getId())
                .getResultList();
    }

    public void store(Student student) {
        if (student.getId() == null) {
            em.persist(student);
        } else {
            em.merge(student);
        }
        em.flush();
    }
}
