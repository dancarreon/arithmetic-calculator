package com.calculator.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Table(name = "record")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "operation_id", nullable = false)
    private Operation operation;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    private Integer amount;
    private Integer userBalance;
    private Integer operationResponse;
    private Instant date;
}
