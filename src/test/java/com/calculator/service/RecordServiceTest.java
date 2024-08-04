package com.calculator.service;

import com.calculator.model.Operation;
import com.calculator.model.Record;
import com.calculator.model.Type;
import com.calculator.model.User;
import com.calculator.repository.RecordRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Instant;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RecordServiceTest {

    @MockBean
    private RecordService recordService;
    @MockBean
    private RecordRepository recordRepository;

    @Test
    public void shouldAddRecord() {
        Record newRecord = new Record(
                1L,
                new Operation(1L, Type.ADDITION, 1, null),
                new User(1L, "username", "password", true, null),
                1,
                10,
                200,
                Instant.now()
        );
        when(recordRepository.saveAndFlush(any())).thenReturn(newRecord);
        when(recordService.addRecord(any())).thenReturn(newRecord);
        Record record = recordService.addRecord(newRecord);
        assertThat(record, notNullValue());
    }
}
