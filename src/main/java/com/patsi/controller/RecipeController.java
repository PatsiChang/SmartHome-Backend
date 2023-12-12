package com.patsi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.patsi.bean.Recipe;
import com.patsi.service.RecipeService;
import com.patsi.validator.RecipeRegistrationValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/recipe")
@CrossOrigin
public class RecipeController {
    @Autowired
    private RecipeService recipeService;
    @Autowired
    private RecipeRegistrationValidator recipeRegistrationValidator;

    @PostMapping
    public List<String> registerRecipe(@RequestBody Recipe recipe){
        System.out.println("Success post");
        List<String> errMsgs = recipeRegistrationValidator.validateRecipe(recipe);
        if(errMsgs.isEmpty()){
            recipeService.registerRecipe(recipe);
        }
        return errMsgs;
    }

    @PutMapping
    public List<String> updateRecipe(@RequestBody Recipe recipe){
        System.out.println("Success put");
        List<String> errMsgs = new ArrayList<>();
        try {
            recipeService.updateRecipe(recipe.getRecipeID(), recipe);
        }catch (Exception e){
            errMsgs.add("Unable to update recipe!");
        }
        return errMsgs;
    }

    @GetMapping
    public List<Recipe> getRecipe(){
//        System.out.print("Success get");
            return recipeService.getRecipe();
    }


    @DeleteMapping
    public void deleteRecipe(@RequestBody String recipeName){
//            System.out.println(recipeName);
            recipeService.deleteRecipe(recipeName);
            System.out.println("Success deleted");
//        List<String> errMsgs = new ArrayList<>();
//        try {
//
//        } catch (Exception e) {
//            errMsgs.add("Cannot delete recipe!");
//        }
    }
}
