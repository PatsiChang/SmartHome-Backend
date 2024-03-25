package com.patsi.controller;

import com.patsi.annotations.RequireLoginSession;
import com.patsi.bean.Recipe;
import com.patsi.service.RecipeService;
import com.patsi.service.UserProfileService;
import com.patsi.utils.ListHelper;
import com.patsi.validator.RecipeRegistrationValidator;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
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

    @GetMapping("getMyRecipe")
    @RequireLoginSession
    public List<Recipe> getRecipe() {
        return recipeService.getRecipe(userProfileService.getUidFromToken());
    }

    @PostMapping
    @RequireLoginSession
    public void registerRecipe(@RequestBody @Valid Recipe recipe) {
        log.info("Inside Controller Register Recipe");
        String userUid = userProfileService.getUidFromToken();
        if (!recipeRegistrationValidator.validateRecipeName(recipe.getRecipeName()))
            recipeService.registerRecipe(recipe, userUid);
    }

    @PutMapping("/addRecipeIcon")
    public String updateRecipeIcon(@RequestParam("recipeIcon") MultipartFile recipeIcon)
        throws IOException {
        log.info("Inside Controller Update Recipe Icon");
        String recipeIconId = UUID.randomUUID().toString();
        recipeService.updateRecipeIcon(recipeIconId, recipeIcon.getBytes());
        return recipeIconId;
    }

//    @PutMapping
//    public List<String> updateRecipe(@RequestBody Recipe recipe) {
//        log.info("Inside Controller Update Recipe");
//        List<String> errMsgs = ListHelper.newList();
//        try {
//            recipeService.updateRecipe(recipe.getRecipeID(), recipe);
//        } catch (Exception e) {
//            errMsgs.add("Unable to update recipe!");
//        }
//        return errMsgs;
//    }

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
