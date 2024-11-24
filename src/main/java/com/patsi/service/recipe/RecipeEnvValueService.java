package com.patsi.service.recipe;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;

@Service
public class RecipeEnvValueService {
    @Value("${TransferRecipeIcon}")
    boolean transferRecipeFileFlag;
    @Value("${com.patsi.recipes.icons.path}")
    private String IMAGE_PATH;
    @Value("${com.patsi.recipes.icons.staged.path}")
    private String IMAGE_STAGED_PATH;


    public boolean getRecipeFileFlag() {
        return transferRecipeFileFlag;
    }

    public String getImgPath() {
        return IMAGE_PATH;
    }

    public String getImgStagedPath() {
        return IMAGE_STAGED_PATH;
    }

}
