package com.robertbalazsi.jpatutor;

import com.robertbalazsi.jpatutor.dao.CourseDao;
import com.robertbalazsi.jpatutor.dao.LecturerDao;
import com.robertbalazsi.jpatutor.dao.StudentDao;
import com.robertbalazsi.jpatutor.entity.Course;
import com.robertbalazsi.jpatutor.entity.Lecturer;
import com.robertbalazsi.jpatutor.entity.Student;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

@ContextConfiguration(locations = {"classpath:/application-context-test.xml"})
public class RecursiveFetchTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    StudentDao studentDao;

    @Autowired
    CourseDao courseDao;

    @Autowired
    LecturerDao lecturerDao;

    private List<Student> students;
    private List<Course> courses;
    private List<Lecturer> lecturers;

    @Before
    public void setup() {
        students = Arrays.asList(
                new Student("Robert"),
                new Student("John"),
                new Student("Mark")
        );

        for (Student student : students) {
            studentDao.store(student);
        }

        courses = Arrays.asList(
                new Course("Algebra"),
                new Course("Statistics"),
                new Course("Geometry")
        );

        for (Course course : courses) {
            courseDao.store(course);
        }

        lecturers = Arrays.asList(
                new Lecturer("Prof 1"),
                new Lecturer("Prof 2")
        );

        for (Lecturer lecturer : lecturers) {
            lecturerDao.store(lecturer);
        }

        courses.get(0).setLecturer(lecturers.get(0));
        courseDao.store(courses.get(0));

        students.get(0).getCourses().add(courses.get(0));
        students.get(0).getCourses().add(courses.get(1));
        studentDao.store(students.get(0));
    }

    @Test
    public void shouldReturnAllStudents() {
        List<Student> students = studentDao.getAllStudents();
        assertEquals(3, students.size());
    }

    @Test
    public void shouldReturnWholeRecursiveTree() {
        List<Student> students = studentDao.getStudentsLecturedBy(lecturers.get(0));
        assertEquals(1, students.size());
    }
}
