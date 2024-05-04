package com.patsi.database.repository;

import com.patsi.bean.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, UUID> {
    Recipe save(Recipe recipe);

    List<Recipe> findByUid(String uid);

    List<Recipe> findAll();

    Optional<Recipe> findByRecipeID(UUID recipeID);

    void deleteById(UUID recipeID);
}