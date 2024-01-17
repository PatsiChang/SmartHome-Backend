package com.patsi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.patsi.bean.Recipe;
import com.patsi.service.RecipeService;
import com.patsi.validator.RecipeRegistrationValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@Validated
@RequestMapping("/recipe")
@CrossOrigin
public class RecipeController {
    @Autowired
    private RecipeService recipeService;
    @Autowired
    private RecipeRegistrationValidator recipeRegistrationValidator;

    @PostMapping
    public UUID registerRecipe(@RequestBody @Valid Recipe recipe){
        System.out.println("controller check 1");
        Recipe tmpRecipe = recipe;
        System.out.println("controller check 2");
        if (!recipeRegistrationValidator.validateRecipeName(tmpRecipe.getrecipeName())) {
            return (recipeService.registerRecipe(recipe));
        }else {
            return null;
        }

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

    @PutMapping ("/addRecipeIcon")
    public void updateRecipeIcon(@RequestParam("recipeID") String recipeID, @RequestParam("recipeIcon") MultipartFile recipeIcon) throws IOException {
        // Update recipe according to the ID
        recipeService.updateRecipeIcon(UUID.fromString(recipeID), recipeIcon.getBytes());
    }

    @GetMapping
    public List<Recipe> getRecipe(){
            return recipeService.getRecipe();
    }

    @GetMapping("/getRandomRecipe")
    public Recipe getRandomRecipe(){
        System.out.println("Success get random recipe");
        return recipeService.getRandomRecipe();
    }


    @DeleteMapping
    public void deleteRecipe(@RequestBody UUID recipeID){
        System.out.println("Delete ID: "+ recipeID);
            recipeService.deleteRecipe(recipeID);
            System.out.println("Success deleted");
    }


}
