package com.robertbalazsi.jpatutor.dao;

import com.robertbalazsi.jpatutor.entity.Lecturer;
import com.robertbalazsi.jpatutor.entity.Student;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class StudentDaoImpl implements StudentDao {

    @PersistenceContext
    private EntityManager em;

    public List<Student> getAllStudents() {
        return em.createNamedQuery(Student.GET_ALL, Student.class)
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