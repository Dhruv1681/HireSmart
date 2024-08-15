package com.hiresmart.service;

import com.hiresmart.dao.ApplicationDao;
import com.hiresmart.model.Application;
import com.hiresmart.model.Job;
import com.hiresmart.model.Student;
import com.hiresmart.model.User;
import com.hiresmart.repository.ApplicationRepository;
import com.hiresmart.repository.JobRepository;
import com.hiresmart.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationService {
    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ApplicationDao applicationDao;

    public List<Application> getAllApplications() {
        return applicationRepository.findAll();
    }

    public Application getApplicationById(Long id) {
        return applicationRepository.findById(id).orElse(null);
    }

    public List<Application> getApplicationByJobId(Long jobId) {
        Job job = jobRepository.findById(jobId).orElse(null);
        return job != null ? applicationDao.findByJob(job) : null;
    }

    public List<Application> getApplicationsByStudent(User student) {
        return applicationRepository.findByStudent(student);
    }


    public List<Application> getApplicationByStudentId(Long studentId) {
        User student = userRepository.findById(studentId).orElse(null);
        return student != null ? applicationDao.findByStudent(student) : null;
    }

    public Application saveApplication(Application application) {
        return applicationRepository.save(application);
    }

    public Application updateApplicationStatus(Long applicationId, String status){
        Application application = applicationRepository.findById(applicationId).orElse(null);
        if (application != null) {
            application.setStatus(status);
            return applicationRepository.save(application);
        }
        return null;
    }

    public void deleteApplication(Long id) {
        applicationRepository.deleteById(id);
    }
}
