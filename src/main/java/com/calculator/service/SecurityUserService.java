package com.calculator.service;

import com.calculator.model.SecurityUser;
import com.calculator.model.User;
import com.calculator.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/*
* Security User Service retrieves User details needed by Global AuthenticationManager
* */
@Service
@RequiredArgsConstructor
@Log4j2
public class SecurityUserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty()) {
            log.info("User {} could not be found", username);
            throw new UsernameNotFoundException(username);
        }
        log.info("User {} found", user.get().getUsername());
        return new SecurityUser(user.get());
    }
}
