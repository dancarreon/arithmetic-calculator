package com.calculator.controller;

import com.calculator.model.dto.RecordRecord;
import com.calculator.model.dto.UserRecord;
import com.calculator.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
* User Controller exposes the endpoints for the User information and the history of operations made
* */
@RestController
@RequestMapping(value = "/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/me")
    @ResponseStatus(HttpStatus.OK)
    public UserRecord getAuthenticatedUser() {
        return userService.getAuthenticatedUser();
    }

    @GetMapping("/balance")
    @ResponseStatus(HttpStatus.OK)
    public Integer getBalance() {
        return userService.getUserBalance();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserRecord getUserById(@PathVariable long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/records")
    @ResponseStatus(HttpStatus.OK)
    public List<RecordRecord> getUserRecords() {
        return userService.getUserRecords();
    }
}
