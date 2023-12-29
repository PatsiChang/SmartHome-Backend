package com.patsi.validator;


import com.patsi.bean.Recipe;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

@Component
public class RecipeRegistrationValidator {
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

//    public boolean validateRecipeName(String recipeName){
//        //recipeName must be at least two words
//        Predicate<String> isValidRecipeName = recipeNameTmp ->
//            recipeNameTmp.contains(" ");
//        return isValidRecipeName.test(recipeName);
//    }

}


//Todo: Recipe Names must be Unique