package com.patsi.bean;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class RecipeSlides {
    @Id
    private UUID recipeSlideID;
    private String recipeSlideURL;

    public RecipeSlides() {
    }

    public RecipeSlides(UUID recipeSlideID, String recipeSlideURL) {
        this.recipeSlideID = recipeSlideID;
        this.recipeSlideURL = recipeSlideURL;
    }

    public UUID getRecipeSlideID() {
        return recipeSlideID;
    }

    public void setRecipeSlideID(UUID recipeSlideID) {
        this.recipeSlideID = recipeSlideID;
    }

    public String getRecipeSlideURL() {
        return recipeSlideURL;
    }

    public void setRecipeSlideURL(String recipeSlideURL) {
        this.recipeSlideURL = recipeSlideURL;
    }
}
