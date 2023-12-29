package com.patsi.validator;

import com.patsi.bean.Recipe;
import com.patsi.enums.RecipeType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;
import java.util.UUID;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

//@SpringBootTest
public class RecipeRegistrationValidatorTest {

//    @Autowired
//    private RecipeRegistrationValidator test = new RecipeRegistrationValidator();
//
//    @ParameterizedTest
//    @ValueSource(strings = {"batmy"})
//    public void validateRecipeName(String recipeName){
//        assertFalse(test.validateRecipeName(recipeName));
//    }
}