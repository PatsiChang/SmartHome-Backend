package com.patsi.scheduler;

import com.patsi.database.repository.RecipeRepository;
import com.patsi.utils.FileHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RemoveRecipeItems {
    @Autowired
    private RecipeRepository recipeRepository;
    @Value("${com.patsi.recipes.icons.path}")
    private String IMAGE_PATH;

    Logger log = LoggerFactory.getLogger(RemoveRecipeItems.class);

//    @Scheduled(cron = "* */5 * * * *")
    public void removeRecipeIcon() throws IOException {
        log.info("removeRecipeIcon------------------");
        List<String> existingRecipeImgUrl = recipeRepository.findAll().stream()
            .map(recipe -> recipe.getImgURL())
            .collect(Collectors.toList());
        List<File> allRecipeIcon = FileHelper.getAllFiles(IMAGE_PATH);
        for(File recipeIcon : allRecipeIcon){
            String iconFileName = FileHelper.removeFileExtension(recipeIcon.getName());
            if(!existingRecipeImgUrl.contains(iconFileName)){
                log.info("looping File"+iconFileName);
//                recipeIcon.delete();
            }
        }

    }
}
