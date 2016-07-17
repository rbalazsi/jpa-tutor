package com.robertbalazsi.jpatutor.service;

/**
 * Created by robi on 7/17/16.
 */
public interface BatchProcessor {

    long queryAndProcessStudentsBatch(long minId);
}
