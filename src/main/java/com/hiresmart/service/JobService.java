package com.hiresmart.service;

import com.hiresmart.dao.JobDao;
import com.hiresmart.model.Job;
import com.hiresmart.model.User;
import com.hiresmart.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {
    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private JobDao jobDao;

    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    public Job getJobById(Long id) {
        return jobRepository.findById(id).orElse(null);
    }

    public Job saveJob(Job job) {
        return jobRepository.save(job);
    }

    public void deleteJob(Long id) {
        jobRepository.deleteById(id);
    }

    public List<Job> getJobsByEmployer(User employer) {
        return jobDao.findByEmployer(employer);
    }
}
