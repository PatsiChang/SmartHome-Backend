package com.patsi.bean;

import com.patsi.enums.GroceryType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Map;
import java.util.UUID;

@Entity
public class GroceryItem {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID groceryID;
    private String groceryItemName;
    private GroceryType groceryItemType;
    private String groceryItemCount;
    private String groceryItemPrice;
    private String notes;
    //    private String itemImage;

    public GroceryItem() {
    }

    public GroceryItem(UUID groceryID, String groceryItemName, GroceryType groceryItemType, String groceryItemCount, String groceryItemPrice, String notes) {
        this.groceryID = groceryID;
        this.groceryItemName = groceryItemName;
        this.groceryItemType = groceryItemType;
        this.groceryItemCount = groceryItemCount;
        this.groceryItemPrice = groceryItemPrice;
        this.notes = notes;
    }
    public UUID getGroceryID() {
        return groceryID;
    }

    public void setGroceryID(UUID groceryID) {
        this.groceryID = groceryID;
    }

    public String getGroceryItemName() {
        return groceryItemName;
    }

    public void setGroceryItemName(String groceryItemName) {
        this.groceryItemName = groceryItemName;
    }

    public GroceryType getGroceryItemType() {
        return groceryItemType;
    }

    public void setGroceryItemType(GroceryType groceryItemType) {
        this.groceryItemType = groceryItemType;
    }

    public String getGroceryItemCount() {
        return groceryItemCount;
    }

    public void setGroceryItemCount(String groceryItemCount) {
        this.groceryItemCount = groceryItemCount;
    }

    public String getGroceryItemPrice() {
        return groceryItemPrice;
    }

    public void setGroceryItemPrice(String groceryItemPrice) {
        this.groceryItemPrice = groceryItemPrice;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

}
