package com.hiresmart.dao;

import com.hiresmart.model.Application;
import com.hiresmart.model.Job;
import com.hiresmart.model.User;

import java.util.List;

public interface ApplicationDao {
    List<Application> findByJob(Job job);
    List<Application> findByStudent(User student);
}
