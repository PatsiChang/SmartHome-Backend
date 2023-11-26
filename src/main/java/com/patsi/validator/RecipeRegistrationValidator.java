package com.patsi.validator;


import com.patsi.bean.Recipe;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Component
public class RecipeRegistrationValidator {
    public List<String> validateRecipe(Recipe recipe){

        List<String> errList = new ArrayList<>();

        if (ObjectUtils.isEmpty(recipe.getName())) {
            errList.add("Please enter Recipe Name");
        }
        if (ObjectUtils.isEmpty(recipe.getType())) {
            errList.add("Please choose Recipe Type");
        }
        if (ObjectUtils.isEmpty(recipe.getIngredient())) {
            errList.add("Please enter Ingredient");
        }

        return errList;
    }
}
