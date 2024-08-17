package com.hiresmart.repository;

import com.hiresmart.model.Application;
import com.hiresmart.model.Job;
import com.hiresmart.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {
    List<Application> findByJob(Job job);
    List<Application> findByStudent(User student);
}
