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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@Controller
@CrossOrigin(origins = "http://localhost:8082")
public class UserController {
    @Autowired
    private UserService userService;
    private final static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @GetMapping("/home")
    public String index() {
        return "index";
    }
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
        log.info("登入");
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @DeleteMapping("/users/logout")
    public ResponseEntity<User> logout(HttpSession session){
        session.removeAttribute("user");
        log.info("登出");
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
