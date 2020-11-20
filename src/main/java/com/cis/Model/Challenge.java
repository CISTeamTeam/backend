package com.cis.Model;

public class Challenge {
    private String id;
    private int rewardPoints;
    private String name;
    private String description;
    private double endDate;

    public Challenge() { }

    public Challenge(String id, int rewardPoints, String name, String description, double endDate) {
        this.id = id;
        this.rewardPoints = rewardPoints;
        this.name = name;
        this.description = description;
        this.endDate = endDate;
    }

    public String getId() {
        return id;
    }

    public int getRewardPoints() {
        return rewardPoints;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getEndDate() {
        return endDate;
    }
}
