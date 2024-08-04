package com.calculator.model;

import com.calculator.model.dto.OperationRecord;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Type type;
    private Integer cost;
    @OneToMany(mappedBy = "operation")
    private List<Record> records;

    public OperationRecord toRecord() {
        return new OperationRecord(id, type, cost);
    }
}
