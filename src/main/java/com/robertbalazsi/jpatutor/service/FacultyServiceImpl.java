package com.robertbalazsi.jpatutor.service;

import com.robertbalazsi.jpatutor.dao.CourseDao;
import com.robertbalazsi.jpatutor.dao.LecturerDao;
import com.robertbalazsi.jpatutor.dao.StudentDao;
import com.robertbalazsi.jpatutor.entity.Course;
import com.robertbalazsi.jpatutor.entity.Lecturer;
import com.robertbalazsi.jpatutor.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by robi on 7/17/16.
 */
@Service
@Scope(proxyMode = ScopedProxyMode.INTERFACES)
public class FacultyServiceImpl implements FacultyService {

    private static final int NR_STUDENTS = 1000;

    @Autowired CourseDao courseDao;
    @Autowired StudentDao studentDao;
    @Autowired LecturerDao lecturerDao;

    @Transactional
    public void fillData() {
        Lecturer[] lecturers = generateAndStoreLecturers();

        Course[] courses = generateAndStoreCourses(lecturers);

        generateAndStoreStudents(courses);
    }

    @Transactional
    public List<Student> getStudentsByName(String name) {
        return studentDao.getByName(name);
    }

    private Student[] generateAndStoreStudents(Course[] courses) {
        Student[] students = new Student[NR_STUDENTS];
        for (int i = 0; i < NR_STUDENTS; i++) {
            students[i] = new Student("Student_" + i);

            if (i%2 == 0) {
                students[i].getCourses().add(courses[0]);
            }

            if (i%3 == 0) {
                students[i].getCourses().add(courses[1]);
            }

            if (i%5 == 0) {
                students[i].getCourses().add(courses[2]);
            }

            if (i%7 == 0) {
                students[i].getCourses().add(courses[3]);
            }

            if (i%11 == 0) {
                students[i].getCourses().add(courses[4]);
            }

            if (i%15 == 0) {
                students[i].getCourses().add(courses[5]);
            }
        }

        for (int i = 0; i < NR_STUDENTS; i++) {
            studentDao.store(students[i]);
        }

        return students;
    }

    private Lecturer[] generateAndStoreLecturers() {
        Lecturer[] lecturers = new Lecturer[] {
                new Lecturer("Boian"),
                new Lecturer("Vancea"),
                new Lecturer("Muresan"),
                new Lecturer("Forest"),
                new Lecturer("Lazar")
        };

        for (int i=0; i<lecturers.length; i++) {
            lecturerDao.store(lecturers[i]);
        }

        return lecturers;
    }

    private Course[] generateAndStoreCourses(Lecturer[] lecturers) {
        Course[] courses = new Course[] {
                new Course("Computer Architecture"),
                new Course("Operating Systems"),
                new Course("Object-Oriented Programming"),
                new Course("Mathematical Analysis"),
                new Course("Web Programming"),
                new Course("Advanced Assembly Programming")
        };

        courses[0].setLecturer(lecturers[1]);
        courses[1].setLecturer(lecturers[0]);
        courses[2].setLecturer(lecturers[4]);
        courses[3].setLecturer(lecturers[2]);
        courses[4].setLecturer(lecturers[3]);
        courses[5].setLecturer(lecturers[1]);

        for (int i=0; i<courses.length; i++) {
            courseDao.store(courses[i]);
        }

        return courses;
    }
}
