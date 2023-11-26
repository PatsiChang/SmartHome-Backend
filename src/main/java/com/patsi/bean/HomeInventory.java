package com.patsi.bean;

import com.patsi.enums.GroceryType;
import java.util.UUID;

public class HomeInventory {

    private UUID ID;
    private String name;
    private GroceryType type;
    private int count;

    public int getRequireCount() {
        return requireCount;
    }

    public void setRequireCount(int requireCount) {
        this.requireCount = requireCount;
    }

    private int requireCount;

    public HomeInventory(String name, GroceryType type, int count, int requireCount) {
        this.name = name;
        this.type = type;
        this.count = count;
        this.requireCount = requireCount;
    }

    public UUID getID() {
        return ID;
    }

    public void setID(UUID ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GroceryType getType() {
        return type;
    }

    public void setType(GroceryType type) {
        this.type = type;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
