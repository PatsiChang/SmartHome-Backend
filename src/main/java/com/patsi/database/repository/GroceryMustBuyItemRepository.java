package com.patsi.database.repository;

import com.patsi.bean.GroceryMustBuyItems;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GroceryMustBuyItemRepository extends Repository<GroceryMustBuyItems, UUID> {
    GroceryMustBuyItems save(GroceryMustBuyItems groceryMustBuyItems);

    //Get
    List<GroceryMustBuyItems> findAll();

    Optional<GroceryMustBuyItems> findById(UUID recipeID);

    //Delete
    void deleteById(UUID groceryID);
}
