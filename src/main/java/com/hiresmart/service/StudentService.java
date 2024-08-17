package com.hiresmart.service;

import com.hiresmart.daoImpl.UserDaoImpl;
import com.hiresmart.model.Student;
import com.hiresmart.model.User;
import com.hiresmart.repository.StudentRepository;
import com.hiresmart.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserDaoImpl userDaoImpl;
    @Autowired
    private StudentRepository studentRepository;

    public List<User> getAllStudents() {
        return userRepository.findAll().stream().filter(user -> "STUDENT".equals(user.getRole())).toList();
    }

    public User getStudentById(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null && "STUDENT".equals(user.getRole())) {
            return user;
        }
        return null;
    }

    public void saveStudent(User student) {
        student.setRole("STUDENT");
        userRepository.save(student);
    }

    public void deleteStudent(Long id) {
        userRepository.deleteById(id);
    }

    public User findByUsername(String username) {
        User user = userDaoImpl.findByUsername(username);
        if (user != null && "STUDENT".equals(user.getRole())) {
            return user;
        }
        return null;
    }
}
