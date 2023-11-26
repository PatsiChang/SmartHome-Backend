package com.patsi.service;

import com.patsi.bean.Recipe;
import com.patsi.enums.RecipeType;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class RecipeService {
    List<Recipe> recipeList = new ArrayList<>();

    //Register New Recipe
    public Recipe registerRecipe(Recipe recipe) {
        recipe.setRecipeID(UUID.randomUUID());
        recipeList.add(recipe);
        return recipe;
    }

    //Update Existing Recipe
    public void updateRecipe (UUID id, Recipe recipe){
        Optional<Recipe> recipeTarget = recipeList.stream()
            .filter(r -> r.getRecipeID().equals(id))
            .findFirst();
        if (recipeTarget.isPresent()){
            recipeList.set(recipeList.indexOf(recipeTarget), recipe);
        }
    }

    //Get Existing Recipe
    public List<Recipe> getRecipe (UUID id){
        List<Recipe> recipe = recipeList.stream()
            .filter(r -> r.getRecipeID().equals(id))
            .collect(Collectors.toList());
        return recipe;
    }

    //Delete Recipe
    public void deleteRecipe(UUID id) {
        List<Recipe> recipe = recipeList.stream()
            .filter(r -> r.getRecipeID().equals(id))
            .collect(Collectors.toList());
        recipeList.removeAll(recipe);
    }
}


