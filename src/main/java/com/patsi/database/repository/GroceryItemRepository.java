package com.patsi.database.repository;

import com.patsi.bean.GroceryItem;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GroceryItemRepository extends Repository<GroceryItem, UUID> {
    GroceryItem save(GroceryItem groceryItem);
    //Get
    List<GroceryItem> findAll();
    Optional<GroceryItem> findById(UUID recipeID);
    //Delete
    void deleteById(UUID groceryID);
}
