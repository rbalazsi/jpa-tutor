package com.robertbalazsi.jpatutor.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Entity
@NamedQueries({
        @NamedQuery(name = Student.GET_ALL, query = "SELECT st FROM Student st "),

        //TODO: the key of the demo!!!
        @NamedQuery(name = Student.GET_BY_LECTURER,
                query = "SELECT st FROM Student st JOIN FETCH st.courses c JOIN FETCH c.lecturer lect WHERE lect.id = :lectId")
})
public class Student extends AbstractEntity {

    public static final String GET_ALL = "GET_ALL";
    public static final String GET_BY_LECTURER = "GET_BY_LECTURER";
    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    @ManyToMany
    @JoinTable(name="students_courses", joinColumns=@JoinColumn(name="student_id", referencedColumnName="id"),
            inverseJoinColumns=@JoinColumn(name="course_id", referencedColumnName="id"))
    private List<Course> courses = new ArrayList<Course>();

    public Student(String name) {
        this.name = name;
    }
}
