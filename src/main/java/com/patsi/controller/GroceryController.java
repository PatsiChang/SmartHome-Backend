package com.patsi.controller;

import com.patsi.bean.GroceryItem;
import com.patsi.bean.GroceryMustBuyItems;
import com.patsi.bean.Recipe;
import com.patsi.database.repository.GroceryItemRepository;
import com.patsi.database.repository.GroceryMustBuyItemRepository;
import com.patsi.enums.GroceryBuyState;
import com.patsi.service.GroceryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

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
    @PostMapping ("/groceryMustBuyItem")
    public UUID registerGroceryMustBuyItem(@RequestBody GroceryMustBuyItems groceryMustBuyItem){
        return groceryService.registerMustBuyGroceryItem(groceryMustBuyItem);
    }

    @GetMapping
    public List<GroceryItem> getGroceryItem(){
        return groceryService.getGroceryItem();
    }
    @GetMapping ("/groceryMustBuyItem")
    public List<GroceryMustBuyItems> getGroceryMustBuyItem(){
        return groceryService.getGroceryMustBuyItem();
    }

    @DeleteMapping
    public void deleteGroceryItem(@RequestBody UUID groceryID){
        groceryService.deleteGroceryItem(groceryID);
    }

    @DeleteMapping ("/groceryMustBuyItem")
    public void deleteGroceryMustBuyItem(@RequestBody UUID groceryID){
        groceryService.deleteGroceryMustBuyItem(groceryID);
    }


}
