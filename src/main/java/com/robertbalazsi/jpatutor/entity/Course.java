package com.robertbalazsi.jpatutor.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

@Entity
public class Course extends AbstractEntity {

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "lecturer_id")
    private Lecturer lecturer;

    @Getter
    @Setter
    @ManyToMany(mappedBy = "courses")
    private List<Student> students = new ArrayList<Student>();

    public Course() {
        /* needed by JPA */
    }

    public Course(String name) {
        this.name = name;
    }
}
