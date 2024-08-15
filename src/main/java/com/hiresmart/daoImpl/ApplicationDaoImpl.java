package com.hiresmart.daoImpl;

import com.hiresmart.dao.ApplicationDao;
import com.hiresmart.model.Application;
import com.hiresmart.model.Job;
import com.hiresmart.model.User;
import com.hiresmart.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ApplicationDaoImpl implements ApplicationDao {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Override
    public List<Application> findByJob(Job job) {
        return applicationRepository.findByJob(job);
    }

    @Override
    public List<Application> findByStudent(User student) {
        return applicationRepository.findByStudent(student);
    }
}
