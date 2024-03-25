package com.patsi.validator;


import com.patsi.bean.Recipe;
import com.patsi.database.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

@Component
public class RecipeRegistrationValidator {
    @Autowired
    private RecipeRepository recipeRepository;

//    public List<String> validateRecipe(Recipe recipe){
//        //Recipe Must contain recipeName, Ingredient and Steps
//        List<String> errList = new ArrayList<>();
//        Consumer<Recipe> isValidRecipe = recipeTmp -> {
//            if(recipeTmp.getrecipeName().isBlank()) { errList.add("Please enter Recipe Name"); }
//            if(recipeTmp.getIngredient().isEmpty()) { errList.add("Please enter Ingredient"); }
//            if(recipeTmp.getSteps().isBlank()) { errList.add("Please enter Steps"); }
//        };
//        isValidRecipe.accept(recipe);
//        return errList;
//    }

    public boolean validateRecipeName(String recipeName) {
        //recipeName must be new
        System.out.println("Checking validation inside validation");
        Predicate<String> isExistingRecipeName = (recipeNameTmp) ->
            recipeRepository.findAll().stream().anyMatch(recipe ->

                recipe.getRecipeName().equals(recipeNameTmp)
            );
        return isExistingRecipeName.test(recipeName);
    }

}


//Todo: Recipe Names must be Unique