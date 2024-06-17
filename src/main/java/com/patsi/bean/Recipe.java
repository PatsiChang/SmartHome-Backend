package com.patsi.bean;

import com.common.validation.annotations.IsDisplayFields;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.patsi.database.configuration.JpaJsonConverter;
import com.patsi.enums.RecipeType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
public class Recipe implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID recipeID;
    //Todo get the uid from login management for recipes
    private String uid;
    @NotNull
    @Column(name = "recipename")
    private String recipeName;
    @Enumerated(EnumType.ORDINAL)
    private RecipeType type;
    // binding persistence
    @Convert(converter = JpaJsonConverter.class)
    @Column(name = "ingredient", columnDefinition = "varchar(9999)")
    @IsDisplayFields
    @JsonDeserialize(contentAs = Ingredient.class)
    @JsonSerialize(contentAs = Ingredient.class)
    private List<Ingredient> ingredient;
    @Column(name = "steps", columnDefinition = "varchar(9999)")
    @Convert(converter = JpaJsonConverter.class)
    @IsDisplayFields
    private List<String> steps;
    private String imgURL;


    public Recipe() {
    }

    public Recipe(String recipeName, RecipeType type, List<Ingredient> ingredient, List<String> steps) {
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

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public RecipeType getType() {
        return type;
    }

    public void setType(RecipeType type) {
        this.type = type;
    }

    public List<Ingredient> getIngredient() {
        return ingredient;
    }

    public void setIngredient(List<Ingredient> ingredient) {
        this.ingredient = ingredient;
    }

    public List<String> getSteps() {
        return steps;
    }

    public void setSteps(List<String> steps) {
        this.steps = steps;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }
}