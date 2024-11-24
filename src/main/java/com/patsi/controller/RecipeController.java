package com.patsi.controller;

import com.common.validation.service.ValidatorService;
import com.patsi.annotations.RequireLoginSession;
import com.patsi.bean.Recipe;
import com.patsi.service.recipe.RecipeService;
import com.patsi.service.UserProfileService;
import com.patsi.validator.RecipeRegistrationValidator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RecipeController {
    private final RecipeService recipeService;
    private final RecipeRegistrationValidator recipeRegistrationValidator;
    private final UserProfileService userProfileService;
    private final ValidatorService validatorService;

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
        List<String> errList = validateRecipe(recipe);
        if (errList.isEmpty()) {
            if (recipeRegistrationValidator.validateRecipeName(recipe.getRecipeName())) {
                errList.add("Recipe Name Already Existed!");
            } else recipeService.registerRecipe(recipe, userProfileService.getUidFromToken());
        }
        return errList;
    }

    @PutMapping
    @RequireLoginSession
    public List<String> updateRecipe(@RequestBody @Valid Recipe recipe) {
        List<String> errList = validateRecipe(recipe);
        if (errList.isEmpty()) {
            recipeService.updateRecipe(recipe, userProfileService.getUidFromToken());
        }
        return errList;
    }

    @PutMapping("/addRecipeIcon")
    public String updateRecipeIcon(@RequestParam("recipeIcon") MultipartFile recipeIcon) throws IOException {
        String recipeIconId = UUID.randomUUID().toString();
        recipeService.addImgToStaged(recipeIconId, recipeIcon.getBytes());
        return recipeIconId;
    }

    @GetMapping("/getRandomRecipe")
    public Recipe getRandomRecipe() {
        return recipeService.getRandomRecipe();
    }

    @DeleteMapping
    @RequireLoginSession
    public void deleteRecipe(@RequestBody Recipe recipe) {
        recipeService.deleteRecipe(recipe);
    }
}
