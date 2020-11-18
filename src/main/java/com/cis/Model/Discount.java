package com.cis.Model;

public class Discount {

    private String id;
    private int requiredPoints;
    private String storeName;
    private String name;
    private String description;

    public Discount() {}

    public Discount(String id, int requiredPoints, String storeName, String name, String description) {
        this.id = id;
        this.requiredPoints = requiredPoints;
        this.storeName = storeName;
        this.name = name;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public int getRequiredPoints() {
        return requiredPoints;
    }

    public String getStoreName() {
        return storeName;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
