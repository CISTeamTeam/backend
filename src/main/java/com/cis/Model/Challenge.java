package com.cis.Model;

public class Challenge {
    private String id;
    private int rewardPoints;
    private String name;
    private String description;
    double endDate;

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

    public void setId(String id) {
        this.id = id;
    }

    public int getRewardPoints() {
        return rewardPoints;
    }

    public void setRewardPoints(int rewardPoints) {
        this.rewardPoints = rewardPoints;
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

    public double getEndDate() {
        return endDate;
    }

    public void setEndDate(double endDate) {
        this.endDate = endDate;
    }
}
