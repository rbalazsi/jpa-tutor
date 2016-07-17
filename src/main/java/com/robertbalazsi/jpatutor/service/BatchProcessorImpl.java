package com.robertbalazsi.jpatutor.service;

import com.robertbalazsi.jpatutor.dao.StudentDao;
import com.robertbalazsi.jpatutor.entity.Course;
import com.robertbalazsi.jpatutor.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by robi on 7/17/16.
 */
@Service
public class BatchProcessorImpl implements BatchProcessor {

    @Autowired
    StudentDao studentDao;

    @Transactional
    public long queryAndProcessStudentsBatch(long minId) {
        List<Student> students = studentDao.getSubset(minId);
        if (!students.isEmpty()) {
            processBatch(students);
            return students.get(students.size()-1).getId();
        }

        return 0;
    }

    private void processBatch(List<Student> students) {
        for (Student student : students) {
            List<Course> courses = student.getCourses();
            for (Course course : courses) {
                course.getLecturer();
            }
        }
    }
}
