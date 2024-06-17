package com.patsi.controller;

import com.common.validation.service.MaskingService;
import com.patsi.annotations.RequireLoginSession;
import com.patsi.bean.Recipe;
import com.patsi.service.RecipeService;
import com.patsi.service.UserProfileService;
import com.patsi.validator.RecipeRegistrationValidator;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@Validated
@RequestMapping("/recipe")
@CrossOrigin
public class RecipeController {
    Logger log = LoggerFactory.getLogger(RecipeController.class);
    @Autowired
    private RecipeService recipeService;
    @Autowired
    private RecipeRegistrationValidator recipeRegistrationValidator;
    @Autowired
    private UserProfileService userProfileService;
    @Autowired
    private MaskingService maskingService;

    @GetMapping("getMyRecipe")
    @RequireLoginSession
    public List<Recipe> getRecipe() {
        return recipeService.getRecipe(userProfileService.getUidFromToken());
    }

    @PostMapping
    @RequireLoginSession
    public void registerRecipe(@RequestBody @Valid Recipe recipe) throws IOException {
        log.info("Inside Controller Register Recipe");
        String userUid = userProfileService.getUidFromToken();
        if (!recipeRegistrationValidator.validateRecipeName(recipe.getRecipeName()))
            recipeService.registerRecipe(recipe, userUid);
    }

    @PutMapping
    @RequireLoginSession
    public void updateRecipe(@RequestBody Recipe recipe) {
        log.info("In updateRecipe");
        String userUid = userProfileService.getUidFromToken();
        recipeService.updateRecipe(recipe, userUid);
    }

    @PutMapping("/addRecipeIcon")
    public String updateRecipeIcon(@RequestParam("recipeIcon") MultipartFile recipeIcon)
        throws IOException {
        log.info("Inside Controller Update Recipe Icon");
        String recipeIconId = UUID.randomUUID().toString();
        recipeService.addImgToStaged(recipeIconId, recipeIcon.getBytes());
        return recipeIconId;
    }

    @GetMapping("/getRandomRecipe")
    public Recipe getRandomRecipe() {
        log.info("Inside Controller Get Random Recipe");
        return recipeService.getRandomRecipe();
    }

    @DeleteMapping
    @RequireLoginSession
    public void deleteRecipe(@RequestBody Recipe recipe) {
        log.info("Inside Controller Delete Recipe");
        recipeService.deleteRecipe(recipe);
    }
}
