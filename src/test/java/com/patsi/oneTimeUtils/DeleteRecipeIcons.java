package com.patsi.oneTimeUtils;

import com.patsi.utils.FileHelper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

class DeleteRecipeIcons {

    public static void main(String[] args) throws IOException {
        Properties applicationProperties = new Properties();
        applicationProperties.load(new FileInputStream("src/main/resources/application.properties"));
        String imagePath = applicationProperties.get("spring.web.resources.static-locations[0]").toString();


//        FileHelper.deleteFile(imagePath, recipe.getRecipeID().toString());
    }
}