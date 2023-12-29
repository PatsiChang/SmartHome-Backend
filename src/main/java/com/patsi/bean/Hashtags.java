package com.patsi.bean;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class Hashtags {
    //This helps filter recipes / Grocery Items
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID hashtagID;
    private String value;
    private int countOfUsage;

    public UUID getHashtagID() {
        return hashtagID;
    }

    public void setHashtagID(UUID hashtagID) {
        this.hashtagID = hashtagID;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getCountOfUsage() {
        return countOfUsage;
    }

    public void setCountOfUsage(int countOfUsage) {
        this.countOfUsage = countOfUsage;
    }
}
