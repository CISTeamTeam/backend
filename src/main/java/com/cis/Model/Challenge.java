package com.cis.Model;

public class Challenge {
    private String id;
    private String name;
    private String description;
    private int rewardPoints;
    private double endDate;

    public Challenge() { }

    public Challenge(String id, String name, String description, int rewardPoints, double endDate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.rewardPoints = rewardPoints;
        this.endDate = endDate;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getRewardPoints() {
        return rewardPoints;
    }

    public double getEndDate() {
        return endDate;
    }
}
