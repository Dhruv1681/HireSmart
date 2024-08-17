package com.hiresmart.daoImpl;

import com.hiresmart.constants.Roles;
import com.hiresmart.dao.EmployerDao;
import com.hiresmart.model.User;
import org.springframework.stereotype.Repository;

@Repository
public class EmployerDaoImpl extends UserDaoImpl implements EmployerDao {

    @Override
    public User findByUsername(String username) {
        return userRepository.findAll().stream().filter(x -> x.getUsername().equals(username)&&x.getRole().equals(Roles.EMPLOYER)).findFirst().orElse(null);
    }
}