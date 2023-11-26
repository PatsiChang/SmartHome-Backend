package com.patsi.enums;

public enum GroceryType {

    FRESH_FOOD("Fresh Food"),
    FROZEN_FOOD("Frozen Food"),
    BEVERAGE("Beverage"),
    BAKERY("Bakery"),
    SNACKS("Snacks"),
    HOUSEHOLD("Household"),
    PETS("Pets");

    private String label;

    GroceryType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
