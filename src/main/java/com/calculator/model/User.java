package com.calculator.model;

import com.calculator.model.dto.UserRecord;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String password;
    private boolean status;
    @OneToMany(mappedBy = "user")
    private List<Record> records;

    public UserRecord toRecord() {
        return new UserRecord(id, username, password, status);
    }
}
