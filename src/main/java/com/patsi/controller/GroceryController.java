package com.patsi.controller;

import com.patsi.bean.GroceryItem;
import com.patsi.bean.GroceryMustBuyItems;
import com.patsi.interceptors.LoggingInterceptor;
import com.patsi.service.GroceryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    Logger log = LoggerFactory.getLogger(LoggingInterceptor.class);
    @Autowired
    private GroceryService groceryService;

    @PostMapping
    public UUID registerGroceryItem(@RequestBody GroceryItem groceryItem){
        log.info("Inside Controller Register Grocery Item");
        return groceryService.registerGroceryItem(groceryItem);
    }

    @PostMapping ("/groceryMustBuyItem")
    public UUID registerGroceryMustBuyItem(@RequestBody GroceryMustBuyItems groceryMustBuyItem){
        log.info("Inside Controller Register Grocery MustBuy Item");
        return groceryService.registerMustBuyGroceryItem(groceryMustBuyItem);
    }

    @GetMapping
    public List<GroceryItem> getGroceryItem(){
        log.info("Inside Controller Get Grocery Item");
        return groceryService.getGroceryItem();
    }

    @GetMapping ("/groceryMustBuyItem")
    public List<GroceryMustBuyItems> getGroceryMustBuyItem(){
        log.info("Inside Controller Get Grocery Must Buy Item");
        return groceryService.getGroceryMustBuyItem();
    }

    @DeleteMapping
    public void deleteGroceryItem(@RequestBody UUID groceryID){
        log.info("Inside Controller Delete Grocery Item");
        groceryService.deleteGroceryItem(groceryID);
    }

    @DeleteMapping ("/groceryMustBuyItem")
    public void deleteGroceryMustBuyItem(@RequestBody UUID groceryID){
        log.info("Inside Controller Register Grocery Must Buy Item");
        groceryService.deleteGroceryMustBuyItem(groceryID);
    }
}
