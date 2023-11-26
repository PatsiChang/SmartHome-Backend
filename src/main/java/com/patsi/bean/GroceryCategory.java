package com.patsi.bean;

import com.patsi.enums.GroceryType;

public class GroceryCategory {
    private GroceryType groceryType;
    private String label;

    public GroceryCategory(GroceryType groceryType, String label) {
        this.groceryType = groceryType;
        this.label = label;
    }

    public GroceryType getGroceryType() {
        return groceryType;
    }

    public String getLabel() {
        return label;
    }
}
