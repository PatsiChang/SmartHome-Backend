package com.patsi.service;

import com.patsi.bean.Recipe;
import com.patsi.database.repository.RecipeRepository;
import com.patsi.enums.RecipeType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class RecipeService {
    @Autowired
    private RecipeRepository recipeRepository;

    List<Recipe> recipeList = new ArrayList<>();
    {
        recipeList.add(new Recipe("123", RecipeType.BREAKFAST, Map.of("apple", "12"), "apple"));
        recipeList.add(new Recipe("456", RecipeType.BREAKFAST, Map.of("apple", "12"), "banana"));
    }

    //Register New Recipe
    public Recipe registerRecipe(Recipe recipe) {
        recipeRepository.save(recipe);
//        recipe.setRecipeID(UUID.randomUUID());
//        recipeList.add(recipe);
        return recipe;
    }

    //Update Existing Recipe
    public void updateRecipe (UUID id, Recipe recipe){
//  recipe.setRecipeID(id);
//  check if recipe exist
        recipeRepository.save(recipe);
        Optional<Recipe> recipeTarget = recipeList.stream()
            .filter(r -> r.getRecipeID().equals(id))
            .findFirst();
        if (recipeTarget.isPresent()){
            recipeList.set(recipeList.indexOf(recipeTarget), recipe);
        }
    }

    //Get Existing Recipe
    public List<Recipe> getRecipe (){
        return recipeRepository.findAll();
    }

    //Delete Recipe
    public void deleteRecipe(String recipeName) {
        List<Recipe> recipe = recipeList.stream()
            .filter(r -> r.getrecipeName().equals(recipeName))
            .collect(Collectors.toList());
        recipeList.removeAll(recipe);
        recipeList.forEach((r) -> System.out.println(r.getrecipeName()));

    }
}


