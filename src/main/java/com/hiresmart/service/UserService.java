package com.hiresmart.service;

import com.hiresmart.dao.UserDao;
import com.hiresmart.model.User;
import com.hiresmart.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private StudentService studentService;
    @Autowired
    private UserRepository userRepository;

    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    public User getUserById(Long id) {
        return userDao.findById(id);
    }

    public void saveUser(User user) {
//        String encodedPassword = passwordEncoder.encode(user.getPassword());
//        user.setPassword(encodedPassword);
        userDao.save(user);
    }

    public void deleteUser(Long id) {
        userDao.deleteById(id);
    }

    public User updateUser(User user) {
        return userDao.update(user);
    }

    public List<User> findUsersByJob(Long jobid){
        return userDao.findUsersByJob(jobid);
    }
}
