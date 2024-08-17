package com.hiresmart.repository;

import com.hiresmart.model.Employer;
import com.hiresmart.model.Job;
import com.hiresmart.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
    List<Job> findByEmployer(User employer);
}
