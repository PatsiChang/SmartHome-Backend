package com.patsi.enums;

public enum GroceryType {

    Carbohydrates("Carbohydrates"),
    MeatEggs("MeatEggs"),
    Bakery("Bakery"),
    Frozen("Frozen"),
    Fruits("Fruits"),
    Vegetables("Vegetables"),
    Beverage("Beverage"),
    Household("Household"),
    Pets("Pets"),
    Others("Others");

    private String label;

    GroceryType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
