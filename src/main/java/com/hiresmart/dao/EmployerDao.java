package com.hiresmart.dao;

import com.hiresmart.model.User;

public interface EmployerDao {
    User findByUsername(String username);
}
