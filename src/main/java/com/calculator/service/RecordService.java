package com.calculator.service;

import com.calculator.model.Record;
import com.calculator.repository.RecordRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

/*
* Record service retrieves User record history
* */
@Service
@RequiredArgsConstructor
@Log4j2
public class RecordService {

    private final RecordRepository recordRepository;

    @Transactional
    public Record addRecord(Record record) {
        log.info("Adding new Record: {}", record);
        return recordRepository.saveAndFlush(record);
    }
}
