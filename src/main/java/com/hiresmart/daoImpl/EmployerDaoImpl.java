package com.hiresmart.daoImpl;

import com.hiresmart.constants.Roles;
import com.hiresmart.dao.EmployerDao;
import com.hiresmart.dao.UserDao;
import com.hiresmart.model.Employer;
import com.hiresmart.model.User;
import com.hiresmart.repository.EmployerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EmployerDaoImpl extends UserDaoImpl implements EmployerDao {

    @Override
    public User findByUsername(String username) {
        return userRepository.findAll().stream().filter(x -> x.getUsername().equals(username)&&x.getRole().equals(Roles.EMPLOYER)).findFirst().orElse(null);
    }
}