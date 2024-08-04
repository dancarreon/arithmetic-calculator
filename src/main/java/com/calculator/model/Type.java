package com.calculator.model;

import lombok.Getter;

@Getter
public enum Type {
    ADDITION("A"), SUBTRACTION("S"), MULTIPLICATION("M"), DIVISION("D"), SQUARE_ROOT("SR"), RANDOM_STRING("RS");

    private final String type;

    Type(String type) {
        this.type = type;
    }
}
