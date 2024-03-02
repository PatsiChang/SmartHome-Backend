package com.patsi.database.repository;

import com.patsi.bean.RecipeSlides;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RecipeSlidesRepository extends Repository<RecipeSlides, UUID> {
    //Post
    RecipeSlides save(RecipeSlides recipeSlide);
    //Get
    List<RecipeSlides> findAll();
    Optional<RecipeSlides> findByRecipeSlideID(UUID recipeSlideID);
    //Delete
    void deleteByRecipeSlideID(UUID recipeSlideID);
}
