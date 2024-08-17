package com.hiresmart.repository;

import com.hiresmart.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface  UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    @Query("SELECT u FROM User u JOIN u.applications a WHERE a.job.id = :jobId")
    List<User> findUsersByJob(@Param("jobId") Long jobId);

}
