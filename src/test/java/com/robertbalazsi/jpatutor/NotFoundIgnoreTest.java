package com.robertbalazsi.jpatutor;

import com.robertbalazsi.jpatutor.dao.ScholarshipDao;
import com.robertbalazsi.jpatutor.dao.StudentDao;
import com.robertbalazsi.jpatutor.entity.Scholarship;
import com.robertbalazsi.jpatutor.entity.Student;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import static org.junit.Assert.assertNull;

/**
 * Created by robi on 7/24/16.
 */
@ContextConfiguration(locations = {"classpath:/application-context-test.xml"})
public class NotFoundIgnoreTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    StudentDao studentDao;

    @Autowired
    ScholarshipDao scholarshipDao;

    @Test
    public void shouldReturnNullOnOrphanEntityAccess() {
        Scholarship scholarship = new Scholarship("Oxford - 2nd term");
        scholarshipDao.store(scholarship);

        Student student = new Student("John");
        student.setScholarship(scholarship);
        studentDao.store(student);

        scholarshipDao.remove(scholarship);

        Student resultStudent = studentDao.getById(student.getId());
        assertNull(resultStudent.getScholarship());
    }
}
