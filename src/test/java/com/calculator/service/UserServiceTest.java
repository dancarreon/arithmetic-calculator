package com.calculator.service;

import com.calculator.controller.GenericControllerTest;
import com.calculator.model.Record;
import com.calculator.model.Type;
import com.calculator.model.User;
import com.calculator.model.dto.OperationRecord;
import com.calculator.model.dto.RecordRecord;
import com.calculator.model.dto.UserRecord;
import com.calculator.repository.RecordRepository;
import com.calculator.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@WithMockUser(username = "user1@test.com")
public class UserServiceTest {

    @MockBean
    private UserRepository userRepository;
    @MockBean
    private UserService userService;
    @MockBean
    private RecordRepository recordRepository;

    @Before
    public void beforeEach() {
        when(userService.getAuthenticatedUser()).thenReturn(new UserRecord(1L, "username", "password", true));
        when(userService.getUserById(anyLong())).thenReturn(new UserRecord(1L, "username", "password", true));
        when(userRepository.findById(any())).thenReturn(Optional.of(new User(1L, "username", "password", true, null)));
        when(recordRepository.findAllByUserIdOrderByDateDesc(1L)).thenReturn(new ArrayList<>());
    }

    @Test
    public void shouldGetAuthenticatedUser() {
        UserRecord user = userService.getAuthenticatedUser();
        assertThat(user.username(), equalTo("username"));
    }

    @Test
    public void shouldGetUserById() {
        UserRecord userRecord = userService.getUserById(1L);
        assertThat(userRecord.id(), equalTo(1L));
    }

    @Test
    public void shouldGetUserRecords() {
        List<RecordRecord> userRecords = userService.getUserRecords();
        assertThat(userRecords, notNullValue());
    }

    @Test
    public void shouldGetUserBalance() {
        Integer userBalance = userService.getUserBalance();
        assertThat(userBalance, equalTo(0));
    }
}
