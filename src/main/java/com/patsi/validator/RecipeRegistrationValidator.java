package com.patsi.validator;

import com.patsi.database.repository.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Predicate;

@Component
@Slf4j
public class RecipeRegistrationValidator {
    @Autowired
    private RecipeRepository recipeRepository;

    public boolean validateRecipeName(String recipeName) {
        log.info("Validating Recipe Name");
        Predicate<String> isExistingRecipeName = (recipeNameTmp) ->
            recipeRepository.findAll().stream().anyMatch(recipe ->
                recipe.getRecipeName().equals(recipeNameTmp)
            );
        return isExistingRecipeName.test(recipeName);
    }
}
