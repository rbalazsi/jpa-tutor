package com.robertbalazsi.jpatutor.dao;

import com.robertbalazsi.jpatutor.entity.Lecturer;
import com.robertbalazsi.jpatutor.entity.Student;

import java.util.List;

public interface StudentDao {

    List<Student> getAllStudents();

    List<Student> getSubset(long maxId);

    List<Student> getByName(String name);

    List<Student> getStudentsLecturedBy(Lecturer lecturer);

    void store(Student student);
}
