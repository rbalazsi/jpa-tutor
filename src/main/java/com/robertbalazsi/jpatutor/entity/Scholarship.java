package com.robertbalazsi.jpatutor.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

/**
 * Created by robi on 7/24/16.
 */
@Entity
public class Scholarship extends AbstractEntity {

    @Getter
    @Setter
    private String name;

    public Scholarship() {
        /* needed by JPA */
    }

    public Scholarship(String name) {
        this.name = name;
    }
}
