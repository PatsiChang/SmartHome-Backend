package com.patsi.service;

import com.patsi.bean.Recipe;
import com.patsi.bean.RecipeSlides;
import com.patsi.database.repository.RecipeSlidesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

@Service
public class RecipeSlidesService {

    @Autowired
    private RecipeSlidesRepository recipeSlidesRepository;

    @Value("${spring.web.resources.static-locations[4]}")
    private String IMAGE_PATH;

    public void addRecipeSlides(byte[] recipeSlide) throws IOException {
        UUID recipeSlidesID = UUID.randomUUID();
        File f = new File(IMAGE_PATH + recipeSlidesID + ".jpg");
        try (FileOutputStream outputStream = new FileOutputStream(f)) {
            outputStream.write(recipeSlide);
        }
        RecipeSlides newRecipeSlide = new RecipeSlides(recipeSlidesID, recipeSlidesID.toString());
        recipeSlidesRepository.save(newRecipeSlide);
    }

    public boolean deleteRecipeSlides(UUID recipeSlidesID) {
        recipeSlidesRepository.deleteByRecipeSlideID(recipeSlidesID);
        return true;
    }
}
