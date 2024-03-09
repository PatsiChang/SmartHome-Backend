package com.patsi.database.repository;

import com.patsi.bean.Recipe;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RecipeRepository extends Repository<Recipe, UUID> {
    //Post
    Recipe save(Recipe recipe);

    //Get
    List<Recipe> findAll();

    Optional<Recipe> findById(UUID recipeID);

    //Delete
    void deleteById(UUID recipeID);
}