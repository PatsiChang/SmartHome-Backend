package com.patsi.controller;

import com.patsi.bean.GroceryItem;
import com.patsi.bean.Recipe;
import com.patsi.service.GroceryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@Validated
@RequestMapping("/groceryItem")
@CrossOrigin
public class GroceryController {
    @Autowired
    private GroceryService groceryService;

    @PostMapping
    public UUID registerGroceryItem(@RequestBody GroceryItem groceryItem){
           return groceryService.registerGroceryItem(groceryItem);
    }

    @GetMapping
    public List<GroceryItem> getGroceryItem(){
        return groceryService.getGroceryItem();
    }

    @DeleteMapping
    public void deleteGroceryItem(@RequestBody UUID groceryID){
        groceryService.deleteGroceryItem(groceryID);
    }


}
