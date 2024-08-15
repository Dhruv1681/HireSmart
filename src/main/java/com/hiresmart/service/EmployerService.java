package com.hiresmart.service;

import com.hiresmart.dao.EmployerDao;
import com.hiresmart.model.Employer;
import com.hiresmart.model.User;
import com.hiresmart.repository.EmployerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployerService {
    @Autowired
    private EmployerRepository employerRepository;

    @Autowired
    private EmployerDao employerDao;

    public List<Employer> findAll() {
        return employerRepository.findAll();
    }

    public Employer saveEmployer(Employer employer) {
        return employerRepository.save(employer);
    }

    public Employer findEmployerById(Long id) {
        return employerRepository.findById(id).orElse(null);
    }

    public User findByUsername(String username) {
        return employerDao.findByUsername(username);
    }

}
