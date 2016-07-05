package com.robertbalazsi.jpatutor.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Entity
public class Lecturer extends AbstractEntity {

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    @OneToMany(mappedBy = "lecturer")
    private List<Course> courses = new ArrayList<Course>();

    public Lecturer(String name) {
        this.name = name;
    }
}
