package com.patsi.service;

import com.patsi.bean.GroceryItem;
import com.patsi.bean.GroceryMustBuyItems;
import com.patsi.database.repository.GroceryItemRepository;
import com.patsi.database.repository.GroceryMustBuyItemRepository;
import com.patsi.interceptors.LoggingInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class GroceryService {
    Logger log = LoggerFactory.getLogger(LoggingInterceptor.class);
    @Autowired
    private GroceryItemRepository groceryItemRepository;
    @Autowired
    private GroceryMustBuyItemRepository groceryMustBuyItemRepository;

    /////////////////////////Good To Buy//////////////////////////
    //Register New GroceryItem
    public UUID registerGroceryItem(GroceryItem groceryItem) {
        groceryItemRepository.save(groceryItem);
        return groceryItem.getGroceryID();
    }
    //Get Existing GroceryItem
    public List<GroceryItem> getGroceryItem (){
        return groceryItemRepository.findAll();

    }
    //Delete GroceryItem
    public void deleteGroceryItem(UUID groceryID) {
        groceryItemRepository.deleteById(groceryID);
    }

    /////////////////////////Must Buy//////////////////////////
    //Register New MustBuy Items
    public UUID registerMustBuyGroceryItem(GroceryMustBuyItems groceryMustBuyItems) {
        groceryMustBuyItemRepository.save(groceryMustBuyItems);
        return groceryMustBuyItems.getGroceryID();
    }

    //Get Existing Must Buy GroceryItem
    public List<GroceryMustBuyItems> getGroceryMustBuyItem (){
        return groceryMustBuyItemRepository.findAll();

    }
    //Delete GroceryItem
    public void deleteGroceryMustBuyItem(UUID groceryID) {
        groceryMustBuyItemRepository.deleteById(groceryID);
        log.info("Successfully deleted groceryID");

    }


}
