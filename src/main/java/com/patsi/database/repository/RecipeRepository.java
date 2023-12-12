package com.patsi.database.repository;

import com.patsi.bean.Recipe;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RecipeRepository extends Repository<Recipe, UUID> {
    Recipe save(Recipe recipe);
    List<Recipe> findAll();
    Optional<Recipe> findById(UUID recipeID);
}