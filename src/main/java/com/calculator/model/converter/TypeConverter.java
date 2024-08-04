package com.calculator.model.converter;

import com.calculator.model.Type;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

/*
* Class that converts Type Enums to String and vice versa, needed by JPA to store/retrieve entries from the DB
* */
@Converter(autoApply = true)
public class TypeConverter implements AttributeConverter<Type, String> {

    @Override
    public String convertToDatabaseColumn(Type type) {
        if (type == null) {
            return null;
        }
        return type.getType();
    }

    @Override
    public Type convertToEntityAttribute(String type) {
        if (type == null) {
            return null;
        }

        return Stream.of(Type.values())
                .filter(t -> t.getType().equals(type))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
