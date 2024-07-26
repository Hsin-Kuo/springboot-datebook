package com.hsinkuo.springbootdatebook.dao;

import com.hsinkuo.springbootdatebook.dto.UserRegisterRequest;
import com.hsinkuo.springbootdatebook.model.User;

public interface UserDao {

    Integer createUser(UserRegisterRequest userRegisterRequest);

    User getUserById(Integer userId);
}
