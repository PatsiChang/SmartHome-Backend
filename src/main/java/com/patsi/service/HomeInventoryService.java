package com.patsi.service;

import com.patsi.bean.HomeInventory;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class HomeInventoryService {
    List<HomeInventory> homeInventoryList = new ArrayList<>();

    //Create New Home Inventory
    public HomeInventory createNewInventory(HomeInventory item) {
        item.setID(UUID.randomUUID());
        homeInventoryList.add(item);
        return item;
    }

    //Update Home Inventory Items
    public HomeInventory updateInventory(HomeInventory item) {
        int itemIdx = getInventoryIdx(item);
        if (itemIdx != -1) {
            homeInventoryList.set(itemIdx, item);
        }
        return item;
    }

    //Get HomeInventoryIdx from homeInventoryList
    public int getInventoryIdx(HomeInventory item) {
        for (int i = 0; i < homeInventoryList.size(); i++) {
            if (homeInventoryList.get(i).getID() == item.getID()) {
                return i;
            }
        }
        return -1;
    }

}
