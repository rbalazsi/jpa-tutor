package com.robertbalazsi.jpatutor.dao;

import com.robertbalazsi.jpatutor.entity.Scholarship;

/**
 * Created by robi on 7/24/16.
 */
public interface ScholarshipDao {

    void store(Scholarship scholarship);

    void remove(Scholarship scholarship);
}
