package com.hsinkuo.springbootdatebook.service;

import com.hsinkuo.springbootdatebook.dto.UserRegisterRequest;
import com.hsinkuo.springbootdatebook.model.User;

public interface UserService {

    Integer register(UserRegisterRequest userRegisterRequest);

    User getUserById(Integer userId);
}
