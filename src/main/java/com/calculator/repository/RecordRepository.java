package com.calculator.repository;

import com.calculator.model.Record;
import com.calculator.model.dto.RecordRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecordRepository extends JpaRepository<Record, Integer> {
    List<RecordRecord> findAllByUserIdOrderByDateDesc(long userId);
}
