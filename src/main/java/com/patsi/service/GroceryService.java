package com.patsi.service;

import com.patsi.bean.GroceryItem;
import com.patsi.database.repository.GroceryItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class GroceryService {
    @Autowired
    private GroceryItemRepository groceryItemRepository;

    //Register New GroceryItem
    public UUID registerGroceryItem(GroceryItem groceryItem) {
        groceryItemRepository.save(groceryItem);
        return groceryItem.getGroceryID();
    }

    //Get Existing Recipe
    public List<GroceryItem> getGroceryItem (){
        return groceryItemRepository.findAll();

    }

    //Delete Recipe
    public void deleteGroceryItem(UUID groceryID) {
        groceryItemRepository.deleteById(groceryID);
    }



}
