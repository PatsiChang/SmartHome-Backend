package com.patsi.service.recipe;

import com.common.validation.service.MaskingService;
import com.common.validation.utils.ValidationHelper;
import com.patsi.bean.Recipe;
import com.patsi.database.repository.RecipeRepository;
import com.patsi.enums.RecipeType;
import com.patsi.service.UserProfileService;
import com.patsi.utils.FileHelper;
import com.patsi.utils.GenerateIntHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RecipeService {

    private final RecipeRepository recipeRepository;
    private final UserProfileService userProfileService;
    private final RecipeEnvValueService recipeEnvValueService;
    private final MaskingService maskingService;


    //Get Existing Recipe
    public List<Recipe> getRecipe(String uid) {
        return recipeRepository.findByUid(uid).stream()
            .map(recipe -> {
                recipe.setUid(null);
                maskingService.maskSensitiveFields(recipe);
                return recipe;
            })
            .toList();
    }

    //Register New Recipe
    public UUID registerRecipe(Recipe recipe, String userUid) throws IOException {
        recipe.setUid(userUid);
        recipeRepository.save(recipe);
        if (recipeEnvValueService.getRecipeFileFlag()) {
            if (recipe.getImgURL() != null) {
                recipeIconTransfer(recipe);
            }
        }
        return recipe.getRecipeID();
    }

    private void recipeIconTransfer(Recipe recipe) throws IOException {
        FileHelper.transferFile(FileHelper.getFileById(recipeEnvValueService.getImgStagedPath(), recipe.getImgURL())
            , recipeEnvValueService.getImgStagedPath(), recipeEnvValueService.getImgPath());
    }

    public String addImgToStaged(String id, byte[] recipeIcon) throws IOException {
        log.info("In Service: addImgToStaged");
        if (ValidationHelper.isJpeg(recipeIcon)) {
            FileHelper.newFile(recipeEnvValueService.getImgStagedPath(), id, recipeIcon);
            return "";
        } else {
            return "Image format not supported!";
        }
    }

    //Update Existing Recipe
    public void updateRecipe(Recipe recipe, String userUid) {
        recipe.setUid(userUid);
        recipeRepository.save(recipe);
    }

    //Filter Random Recipe List
    public List<Recipe> filterRandomRecipeList(RecipeType recipeType) {
        List<Recipe> tmpRecipeList = recipeRepository.findAll();
        return tmpRecipeList.stream()
            .filter((recipe) -> recipe.getType() == recipeType)
            .collect(Collectors.toList());
    }

    //Todo: Change to Switch
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
            List<Recipe> tmpRecipeList = recipeRepository.findAll();
            if (tmpRecipeList.size() > 0) {
                int randomNumWholeList = (int) (GenerateIntHelper.getGenerateRandomInt(0, tmpRecipeList.size()));
                return tmpRecipeList.get(randomNumWholeList);
            } else
                return null;
        }
    }

    //Delete Recipe
    public void deleteRecipe(Recipe recipe) {
        String userUid = userProfileService.getUidFromToken();
        if (recipeRepository.findByRecipeID(recipe.getRecipeID())
            .orElseGet(null)
            .getUid()
            .equals(userUid)) {
            recipeRepository.deleteById(recipe.getRecipeID());
            FileHelper.deleteFile(recipeEnvValueService.getImgPath(), recipe.getImgURL().toString());
        }
    }
}


