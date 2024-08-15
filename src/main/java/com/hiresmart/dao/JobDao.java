package com.hiresmart.dao;

import com.hiresmart.model.Employer;
import com.hiresmart.model.Job;
import com.hiresmart.model.User;

import java.util.List;

public interface JobDao {
    List<Job> findByEmployer(User employer);
}
