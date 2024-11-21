package com.patsi.controller;

import com.common.validation.service.MaskingService;
import com.common.validation.service.ValidatorService;
import com.patsi.annotations.RequireLoginSession;
import com.patsi.bean.Recipe;
import com.patsi.service.RecipeService;
import com.patsi.service.UserProfileService;
import com.patsi.utils.ListHelper;
import com.patsi.validator.RecipeRegistrationValidator;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RestController
@Validated
@RequestMapping("/recipe")
@CrossOrigin
@Slf4j
public class RecipeController {
    @Autowired
    private RecipeService recipeService;
    @Autowired
    private RecipeRegistrationValidator recipeRegistrationValidator;
    @Autowired
    private UserProfileService userProfileService;
    @Autowired
    private MaskingService maskingService;
    @Autowired
    private ValidatorService validatorService;

    @GetMapping("getMyRecipe")
    @RequireLoginSession
    public List<Recipe> getRecipe() {
        return recipeService.getRecipe(userProfileService.getUidFromToken());
    }

    private List<String> validateRecipe(Recipe recipe) {
        return validatorService.checkAnnotation(recipe);
    }

    @PostMapping
    @RequireLoginSession
    public List<String> registerRecipe(@RequestBody @Valid Recipe recipe) throws IOException {
        log.info("Inside Controller Register Recipe");
        List<String> errList = validateRecipe(recipe);
        if (errList.isEmpty()) {
            if (recipeRegistrationValidator.validateRecipeName(recipe.getRecipeName())) {
                errList.add("Recipe Name Already Existed!");
            } else
                recipeService.registerRecipe(recipe, userProfileService.getUidFromToken());
        }
        return errList;
    }

    @PutMapping
    @RequireLoginSession
    public List<String> updateRecipe(@RequestBody @Valid Recipe recipe) {
        log.info("In updateRecipe");
        String userUid = userProfileService.getUidFromToken();
        List<String> errList = validateRecipe(recipe);
        if (errList.isEmpty()) {
            recipeService.updateRecipe(recipe, userUid);
        }
        return errList;
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
