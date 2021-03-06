package com.robertbalazsi.jpatutor.main;

import com.robertbalazsi.jpatutor.dao.ScholarshipDao;
import com.robertbalazsi.jpatutor.dao.StudentDao;
import com.robertbalazsi.jpatutor.entity.Course;
import com.robertbalazsi.jpatutor.entity.Scholarship;
import com.robertbalazsi.jpatutor.entity.Student;
import com.robertbalazsi.jpatutor.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by robi on 7/17/16.
 */
@Component
public class FacultyManagerApp {

    @Autowired
    FacultyService facultyService;

    @Autowired
    StudentDao studentDao;

    @Autowired
    ScholarshipDao scholarshipDao;

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:/application-context.xml");

        FacultyManagerApp app = context.getBean(FacultyManagerApp.class);
        app.run();
    }

    @Transactional
    public void run() {
        // TODO: comment out below to fill the data
//         facultyService.fillData();

//        List<Student> students = facultyService.getStudentsByName("Student_10");
//        List<Course> studentCourses = students.get(0).getCourses();
//        studentCourses.size();

//        Scholarship scholarship = new Scholarship("Oxford - 2nd term");
//        scholarshipDao.store(scholarship);

        Student student = studentDao.getById(1000l);
        Scholarship scholarship = student.getScholarship();

//        facultyService.processStudents();
    }
}
