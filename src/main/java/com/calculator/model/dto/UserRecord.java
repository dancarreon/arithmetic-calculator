package com.calculator.model.dto;

import com.calculator.model.User;

public record UserRecord(Long id, String username, String password, boolean status) {
    public User toUser() {
        return new User(id, username, password, status, null);
    }
}
