package com.hiresmart.daoImpl;

import com.hiresmart.dao.JobDao;
import com.hiresmart.model.Employer;
import com.hiresmart.model.Job;
import com.hiresmart.model.User;
import com.hiresmart.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JobDaoImpl implements JobDao {

    @Autowired
    private JobRepository jobRepository;

    @Override
    public List<Job> findByEmployer(User employer) {
        return jobRepository.findByEmployer(employer);
    }
}
