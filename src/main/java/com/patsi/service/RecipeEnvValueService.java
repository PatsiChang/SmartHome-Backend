package com.patsi.service;

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


    public Supplier<Boolean> getRecipeFileFlag() {
        return () -> transferRecipeFileFlag;
    }

    public Supplier<String> getImgPath() {
        return () -> IMAGE_PATH;
    }

    public Supplier<String> getImgStagedPath() {
        return () -> IMAGE_STAGED_PATH;
    }

}
