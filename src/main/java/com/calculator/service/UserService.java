package com.calculator.service;

import com.calculator.model.SecurityUser;
import com.calculator.model.User;
import com.calculator.model.dto.RecordRecord;
import com.calculator.model.dto.UserRecord;
import com.calculator.repository.RecordRepository;
import com.calculator.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/*
 * User Service retrieves User information and records
 * */
@Service
@RequiredArgsConstructor
@Log4j2
public class UserService {

    private final UserRepository userRepository;
    private final RecordRepository recordRepository;
    private final Environment environment;

    public UserRecord getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
            SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
            log.info("Retrieving info from authenticated User: {}", securityUser.getUsername());
            return userRepository.findById(securityUser.getId()).map(User::toRecord).orElse(null);
        }
        return null;
    }

    public UserRecord getUserById(long id) {
        log.info("Requesting User with id: {}", id);
        Optional<User> user = userRepository.findById(id);
        return user.map(User::toRecord).orElse(null);
    }

    public List<RecordRecord> getUserRecords() {
        UserRecord user = getAuthenticatedUser();
        List<RecordRecord> userRecords = recordRepository.findAllByUserIdOrderByDateDesc(user.id());
        log.info("Requesting User's Records by user id: {}, records: {}", user.id(), userRecords);
        return userRecords;
    }

    public Integer getUserBalance() {
        List<RecordRecord> userRecords = getUserRecords();
        if (userRecords != null && !userRecords.isEmpty()) {
            return userRecords.getFirst().userBalance();
        }
        return Integer.parseInt(Objects.requireNonNull(environment.getProperty("user.default.balance")));
    }
}
