package com.hsinkuo.springbootdatebook.controller;

import com.hsinkuo.springbootdatebook.dto.UserLoginRequest;
import com.hsinkuo.springbootdatebook.dto.UserRegisterRequest;
import com.hsinkuo.springbootdatebook.model.User;
import com.hsinkuo.springbootdatebook.service.UserService;
import com.hsinkuo.springbootdatebook.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:8082")
public class UserController {
    @Autowired
    private UserService userService;
    private final static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @PostMapping("/users/register")
    public ResponseEntity<User> register(@RequestBody @Valid UserRegisterRequest userRegisterRequest){
        Integer userId = userService.register(userRegisterRequest);

        User user = userService.getUserById(userId);

        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PostMapping("/users/login")
    public ResponseEntity<User> login(@RequestBody @Valid UserLoginRequest userLoginRequest,
                                      HttpSession session){

        User user = userService.login(userLoginRequest);
        session.setAttribute("user", user);
        log.warn("登入");
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }
}
