package com.patsi.bean;

import com.patsi.enums.GroceryBuyState;
import com.patsi.enums.GroceryType;
import jakarta.persistence.*;

import java.util.Map;
import java.util.UUID;

@Entity
public class GroceryItem {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID groceryID;
    private String groceryItemName;
    @Enumerated(EnumType.ORDINAL)
    private GroceryType groceryItemType;
    private String groceryItemCount;
    private String groceryItemPrice;
    private String groceryShop;
    @Enumerated(EnumType.ORDINAL)
    private GroceryBuyState groceryBuyState;
    private String notes;
    //    private String itemImage;

    public GroceryItem() {
    }

    public GroceryItem(UUID groceryID, String groceryItemName, GroceryType groceryItemType, String groceryItemCount, String groceryItemPrice, String groceryShop, GroceryBuyState groceryBuyState, String notes) {
        this.groceryID = groceryID;
        this.groceryItemName = groceryItemName;
        this.groceryItemType = groceryItemType;
        this.groceryItemCount = groceryItemCount;
        this.groceryItemPrice = groceryItemPrice;
        this.groceryShop = groceryShop;
        this.groceryBuyState = groceryBuyState;
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

    public String getGroceryShop() {
        return groceryShop;
    }

    public void setGroceryShop(String groceryShop) {
        this.groceryShop = groceryShop;
    }

    public GroceryBuyState getGroceryBuyState() {
        return groceryBuyState;
    }

    public void setGroceryBuyState(GroceryBuyState groceryBuyState) {
        this.groceryBuyState = groceryBuyState;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

}
