package com.hsinkuo.springbootdatebook.service.impl;

import com.hsinkuo.springbootdatebook.dao.UserDao;
import com.hsinkuo.springbootdatebook.dto.UserRegisterRequest;
import com.hsinkuo.springbootdatebook.model.User;
import com.hsinkuo.springbootdatebook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public Integer register(UserRegisterRequest userRegisterRequest) {
        return userDao.createUser(userRegisterRequest);
    }

    @Override
    public User getUserById(Integer userId) {

        return userDao.getUserById(userId);
    }
}
