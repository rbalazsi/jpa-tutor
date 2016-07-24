package com.robertbalazsi.jpatutor.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = Student.GET_BY_ID, query = "SELECT st FROM Student st WHERE st.id = :id"),
        @NamedQuery(name = Student.GET_ALL, query = "SELECT st FROM Student st "),
        @NamedQuery(name = Student.GET_SUBSET, query = "SELECT st FROM Student st WHERE st.id > :minId"),
        @NamedQuery(name = Student.GET_BY_NAME, query = "SELECT st FROM Student st WHERE st.name = :name"),
        @NamedQuery(name = Student.GET_BY_LECTURER,
                query = "SELECT st FROM Student st JOIN FETCH st.courses c JOIN FETCH c.lecturer lect WHERE lect.id = :lectId")
})
public class Student extends AbstractEntity {

    public static final String GET_BY_ID = "Student.GET_BY_ID";
    public static final String GET_ALL = "Student.GET_ALL";
    public static final String GET_SUBSET = "Student.GET_SUBSET";
    public static final String GET_BY_LECTURER = "Student.GET_BY_LECTURER";
    public static final String GET_BY_NAME = "Student.GET_BY_NAME";

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @NotFound(action = NotFoundAction.IGNORE)
    private Scholarship scholarship;

    @Getter
    @Setter
    @ManyToMany
    @JoinTable(name="students_courses", joinColumns=@JoinColumn(name="student_id", referencedColumnName="id"),
            inverseJoinColumns=@JoinColumn(name="course_id", referencedColumnName="id"))
    private List<Course> courses = new ArrayList<Course>();

    public Student() {
        /* needed by JPA */
    }

    public Student(String name) {
        this.name = name;
    }
}
