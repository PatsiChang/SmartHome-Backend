package com.patsi.database.repository;

import com.patsi.bean.Recipe;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RecipeRepository extends Repository<Recipe, UUID> {
    //Post
    Recipe save(Recipe recipe);

    //Get by UID
    List<Recipe> findByUid(String uid);

    //Get
    List<Recipe> findAll();

    Optional<Recipe> findByRecipeID(UUID recipeID);

    //Delete
    void deleteById(UUID recipeID);
}