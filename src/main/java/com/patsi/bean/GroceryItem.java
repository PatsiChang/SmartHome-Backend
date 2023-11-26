package com.patsi.bean;

import java.util.Map;

public class GroceryItem {
    private String itemImage;
    private String name;
    private String description;
    private int stock;
    private Map<String, Double> price;

    public GroceryItem(String itemImage, String name, String description, int stock, Map<String, Double> price) {
        this.itemImage = itemImage;
        this.name = name;
        this.description = description;
        this.stock = stock;
        this.price = price;
    }

    public String getItemImage() {
        return itemImage;
    }

    public void setItemImage(String itemImage) {
        this.itemImage = itemImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Map<String, Double> getPrice() {
        return price;
    }

    public void setPrice(Map<String, Double> price) {
        this.price = price;
    }
}
