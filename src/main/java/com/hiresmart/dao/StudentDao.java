package com.hiresmart.dao;

import com.hiresmart.model.Student;
import com.hiresmart.model.User;

public interface StudentDao {
    User findByUsername(String username);
}
