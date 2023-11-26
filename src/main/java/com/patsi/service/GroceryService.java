package com.patsi.service;

import com.patsi.bean.GroceryItem;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;

@Service
public class GroceryService {
    //UUID normally stored in DB
    private Map<UUID, GroceryItem> itemsList;

    public GroceryItem getGroceryItem(UUID itemId){
        return itemsList.get(itemId);
    }


}
