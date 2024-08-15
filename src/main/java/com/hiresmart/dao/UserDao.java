package com.hiresmart.dao;

import com.hiresmart.model.User;

import java.util.List;

public interface UserDao {
    User findByUsername(String username);
    List<User> findAll();
    User findById(Long id);
    void save(User user);
    void deleteById(Long id);
    User update(User user);
}
