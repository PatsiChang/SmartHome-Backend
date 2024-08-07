package com.patsi.database.configuration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.patsi.bean.Ingredient;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.io.IOException;
import java.util.List;

//@Converter(autoApply = true)
public class JpaJsonConverter implements AttributeConverter<Object, String> {
    private final static ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(Object meta) {
        try {
            return objectMapper.writeValueAsString(meta);
        } catch (JsonProcessingException ex) {
            return null;
        }
    }

    @Override
    public Object convertToEntityAttribute(String dbData) {
        try {
            return objectMapper.readValue(dbData, Object.class);
        } catch (IOException ex) {
            return null;
        }
    }

}