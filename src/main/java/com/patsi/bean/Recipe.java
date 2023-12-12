package com.patsi.bean;

import com.patsi.database.configuration.JpaJsonConverter;
import com.patsi.enums.RecipeType;
import jakarta.persistence.*;

import java.util.Map;
import java.util.UUID;

@Entity
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID recipeID;

    private String recipeName;
    @Enumerated(EnumType.ORDINAL)
    private RecipeType type;
    // binding persistence
    @Convert(converter = JpaJsonConverter.class)
    private Map<String, String> ingredient;
    private String steps;
    private String imgURL;

    public Recipe() {
    }

    public Recipe(String recipeName, RecipeType type, Map<String, String> ingredient, String steps) {
        this.recipeName = recipeName;
        this.type = type;
        this.ingredient = ingredient;
        this.steps = steps;
    }


    public UUID getRecipeID() {
        return recipeID;
    }

    public void setRecipeID(UUID recipeID) {
        this.recipeID = recipeID;
    }

    public String getrecipeName() {
        return recipeName;
    }

    public void setrecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public RecipeType getType() {
        return type;
    }

    public void setType(RecipeType type) {
        this.type = type;
    }

    public Map<String, String> getIngredient() {
        return ingredient;
    }

    public void setIngredient(Map<String, String> ingredient) {
        this.ingredient = ingredient;
    }

    public String getSteps() {
        return steps;
    }

    public void setSteps(String steps) {
        this.steps = steps;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }
}