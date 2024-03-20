package com.patsi.service;

import com.patsi.bean.Recipe;
import com.patsi.database.repository.RecipeRepository;
import com.patsi.enums.RecipeType;
import com.patsi.interceptors.LoggingInterceptor;
import com.patsi.utils.FileHelper;
import com.patsi.utils.GenerateIntHelper;
import com.patsi.utils.ListHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class RecipeService {
    Logger log = LoggerFactory.getLogger(RecipeService.class);

    @Autowired
    private RecipeRepository recipeRepository;

    @Value("${spring.web.resources.static-locations[3]}")
    private String IMAGE_PATH;

    //Get Existing Recipe
    public List<Recipe> getRecipe(String uid) {
        List<Recipe> finalRecipeList = recipeRepository.findByUid(uid);
        finalRecipeList.forEach((recipe -> {
            recipe.setUid(null);
        }));
        return finalRecipeList;
    }
    //Register New Recipe
    public UUID registerRecipe(Recipe recipe, String userUid) {
        recipe.setUid(userUid);
        List<Recipe.Ingredient> ingredients = recipe.getIngredient();
        recipe.setIngredient(ingredients);
        recipeRepository.save(recipe);
        return recipe.getRecipeID();
    }

    //Add Recipe Icon to folder before assigning to user
    public void updateRecipeIcon(String id, byte[] recipeIcon) throws IOException {
        log.info("In Service: updateRecipeIcon");
        FileHelper.newFile(IMAGE_PATH, id, recipeIcon);
    }

    //Update Existing Recipe
    public void updateRecipe(UUID id, Recipe recipe) {
        recipeRepository.save(recipe);
    }

    //Filter Random Recipe List
    public List<Recipe> filterRandomRecipeList(RecipeType recipeType) {
        List<Recipe> tmpRecipeList = recipeRepository.findAll();
        return tmpRecipeList.stream()
            .filter((recipe) -> recipe.getType() == recipeType)
            .collect(Collectors.toList());
    }

    //Get Random Recipe
    public Recipe getRandomRecipe() {
        Date d = new Date();
        int currHour = d.getHours();
        List<Recipe> returnList;
        if (currHour <= 11 && currHour >= 5) {
            returnList = filterRandomRecipeList(RecipeType.BREAKFAST);
        } else if (currHour >= 12 && currHour <= 14) {
            returnList = filterRandomRecipeList(RecipeType.LUNCH);
        } else if (currHour >= 15 && currHour <= 17) {
            returnList = filterRandomRecipeList(RecipeType.AFTERNOON_TEA);
        } else if (currHour >= 18 && currHour <= 21) {
            returnList = filterRandomRecipeList(RecipeType.DINNER);
        } else {
            returnList = filterRandomRecipeList(RecipeType.DESSERT);
        }
        int randomNum = (int) (GenerateIntHelper.getGenerateRandomInt(0, returnList.size()));
        if (randomNum > 0) {
            return returnList.get(randomNum);
        } else {
            log.info("Checked random recipe");
            List<Recipe> tmpRecipeList = recipeRepository.findAll();
            if (tmpRecipeList.size() > 0) {
                int randomNumWholeList = (int) (GenerateIntHelper.getGenerateRandomInt(0, tmpRecipeList.size()));
                return tmpRecipeList.get(randomNumWholeList);
            } else
                return null;

        }
    }

    //Delete Recipe
    public void deleteRecipe(UUID recipeID) {
        recipeRepository.deleteById(recipeID);
    }
}


