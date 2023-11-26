package com.patsi.controller;

import com.patsi.bean.Recipe;
import com.patsi.service.RecipeService;
import com.patsi.validator.RecipeRegistrationValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/recipe")
public class RecipeController {
    @Autowired
    private RecipeService recipeService;
    @Autowired
    private RecipeRegistrationValidator recipeRegistrationValidator;

    @PostMapping
    public List<String> registerRecipe(@RequestBody Recipe recipe){

        List<String> errMsgs = recipeRegistrationValidator.validateRecipe(recipe);
        if(errMsgs.isEmpty()){
            recipeService.registerRecipe(recipe);
        }
        return errMsgs;
    }

    @PutMapping
    public List<String> updateRecipe(@RequestBody Recipe recipe){

        List<String> errMsgs = new ArrayList<>();
        try {
            recipeService.updateRecipe(recipe.getRecipeID(), recipe);
        }catch (Exception e){
            errMsgs.add("Unable to update recipe!");
        }
        return errMsgs;
    }

    @GetMapping
    public List<String> getRecipe(@RequestBody Recipe recipe){

        List<String> errMsgs = new ArrayList<>();
        try {
            recipeService.getRecipe(recipe.getRecipeID());
        }catch (Exception e){
            errMsgs.add("Unable to find recipe!");
        }
        return errMsgs;
    }


    @DeleteMapping
    public List<String> deleteRecipe(Recipe recipe){
        List<String> errMsgs = new ArrayList<>();
        try {
            recipeService.deleteRecipe(recipe.getRecipeID());
        } catch (Exception e) {
            errMsgs.add("Cannot delete recipe!");
        }
        return errMsgs;
    }
}
