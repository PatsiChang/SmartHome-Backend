package com.patsi.enums;

public enum GroceryBuyState {

    GoodToBuy("GoodToBuy"),
    MustBuy("MustBuy"),
    HomeStock("HomeStock");

    private String label;

    GroceryBuyState(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
