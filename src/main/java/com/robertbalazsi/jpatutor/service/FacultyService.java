package com.robertbalazsi.jpatutor.service;

import com.robertbalazsi.jpatutor.entity.Student;

import java.util.List;

/**
 * Created by robi on 7/17/16.
 */
public interface FacultyService {

    void fillData();

    void processStudents();

    List<Student> getStudentsByName(String name);
}
