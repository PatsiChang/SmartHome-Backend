package com.patsi.controller;

import com.patsi.bean.Recipe;
import com.patsi.service.RecipeService;
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

    @PostMapping
    public UUID registerRecipe(@RequestBody @Valid Recipe recipe) {
        log.info("Inside Controller Register Recipe");
        return (!recipeRegistrationValidator.validateRecipeName(recipe.getRecipeName()))
            ? recipeService.registerRecipe(recipe) : null;
    }

    @PutMapping
    public List<String> updateRecipe(@RequestBody Recipe recipe) {
        log.info("Inside Controller Update Recipe");
        List<String> errMsgs = ListHelper.newList();
        try {
            recipeService.updateRecipe(recipe.getRecipeID(), recipe);
        } catch (Exception e) {
            errMsgs.add("Unable to update recipe!");
        }
        return errMsgs;
    }

    @PutMapping("/addRecipeIcon")
    public void updateRecipeIcon(@RequestParam("recipeID") String recipeID,
                                 @RequestParam("recipeIcon") MultipartFile recipeIcon) throws IOException {
        log.info("Inside Controller Update Recipe Icon");
        recipeService.updateRecipeIcon(UUID.fromString(recipeID), recipeIcon.getBytes());
    }

    @PostMapping("getRecipeByToken")
    public List<Recipe> getRecipe(@RequestBody String token) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8081/logInSession";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "text/plain");
        HttpEntity<String> requestEntity = new HttpEntity<>(token, headers);
        ResponseEntity<String> responseEntity =
            restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
        String result = responseEntity.getBody();
        return recipeService.getRecipe(result);
    }

    @GetMapping("/getRandomRecipe")
    public Recipe getRandomRecipe() {
        log.info("Inside Controller Get Random Recipe");
        return recipeService.getRandomRecipe();
    }

    @DeleteMapping
    public void deleteRecipe(@RequestBody UUID recipeID) {
        log.info("Inside Controller Delete Recipe");
        recipeService.deleteRecipe(recipeID);
    }
}
