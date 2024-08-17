package com.hiresmart.daoImpl;

import com.hiresmart.constants.Roles;
import com.hiresmart.dao.StudentDao;
import com.hiresmart.model.Student;
import com.hiresmart.model.User;
import com.hiresmart.repository.StudentRepository;
import com.hiresmart.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class StudentDaoImpl extends UserDaoImpl implements StudentDao{

    @Override
    public User findByUsername(String username) {
        System.out.println("Finding student by username: " + username);
        return userRepository.findAll().stream().filter(x -> x.getUsername().equals(username)&&x.getRole().equals(Roles.STUDENT)).findFirst().orElse(null);
    }
}
