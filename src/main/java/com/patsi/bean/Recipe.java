package com.patsi.bean;

import com.patsi.enums.RecipeType;

import java.util.Map;
import java.util.UUID;

public class Recipe {
    private UUID recipeID;
    private String name;
    private RecipeType type;
    private Map<String, String> ingredient;
    private String steps;

    public Recipe() {
    }

    public Recipe(String name, RecipeType type, Map<String, String> ingredient, String steps) {
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
