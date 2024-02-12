package com.patsi.service;

import com.patsi.bean.Recipe;
import com.patsi.database.repository.RecipeRepository;
import com.patsi.enums.GroceryType;
import com.patsi.enums.RecipeType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class RecipeService {
    @Autowired
    private RecipeRepository recipeRepository;

    @Value("${spring.web.resources.static-locations[3]}")
    private String IMAGE_PATH;

    //Register New Recipe
    public UUID registerRecipe(Recipe recipe) {
        List<Recipe.Ingredient> ingredients = recipe.getIngredient();
//        List<Recipe.Steps> steplist = recipe.getSteps();
        recipe.setIngredient(ingredients);
//        recipe.setSteps(steplist);
        recipeRepository.save(recipe);
        return recipe.getRecipeID();
    }

    //Update Existing Recipe
    public void updateRecipe (UUID id, Recipe recipe){

        recipeRepository.save(recipe);
    }

    public void updateRecipeIcon (UUID id, byte[] recipeIcon) throws IOException {
        File f = new File(IMAGE_PATH + id +".jpg" );
            try(FileOutputStream outputStream = new FileOutputStream(f)){
                outputStream.write(recipeIcon);
            }
        Recipe recipe = recipeRepository.findById(id).get();
        recipe.setImgURL(id.toString());
        recipeRepository.save(recipe);
    }

    //Get Existing Recipe
    public List<Recipe> getRecipe (){
//        recipeRepository.findAll().stream().forEach((recipe -> {System.out.println(recipe.getIngredient());}));
        return recipeRepository.findAll();
    }

    //Filter Random Recipe List
    public List<Recipe> filterRandomRecipeList (RecipeType recipeType){
        List<Recipe> tmpRecipeList = recipeRepository.findAll();
        return tmpRecipeList.stream()
            .filter((recipe)-> recipe.getType() == recipeType)
            .collect(Collectors.toList());
    }

    //Get Random Recipe
    public Recipe getRandomRecipe(){
        Date d = new Date();
        int currHour = d.getHours();
        List<Recipe> returnList;
        if (currHour <= 11 && currHour >= 5){
            returnList = filterRandomRecipeList(RecipeType.BREAKFAST);
        }else if (currHour >= 12 && currHour <= 14){
            returnList = filterRandomRecipeList(RecipeType.LUNCH);
        }else if (currHour >= 15 && currHour <= 17){
            returnList = filterRandomRecipeList(RecipeType.AFTERNOON_TEA);
        }else if (currHour >= 18 && currHour <= 21){
            returnList = filterRandomRecipeList(RecipeType.DINNER);
        }else{
            returnList = filterRandomRecipeList(RecipeType.DESSERT);
        }
        int randomNum = (int)(Math.random()*returnList.size());
        if (randomNum > 0){
            return returnList.get(randomNum);
        }else{
            System.out.println("Checked random recipe");
            List<Recipe> tmpRecipeList = recipeRepository.findAll();
            int randomNumWholeList = (int)(Math.random()*tmpRecipeList.size());
            return tmpRecipeList.get(randomNumWholeList);
        }
    }

    //Delete Recipe
    public void deleteRecipe(UUID recipeID) {
        recipeRepository.deleteById(recipeID);
    }
}


