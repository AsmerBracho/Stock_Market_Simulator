package com.intelligence_1.stockmarketsimulator.controller.graphql;

public class InvestorGraphQL {

private String name;
private Float budget;
    private int sharesBought;

    public InvestorGraphQL (String name, Float budget, int sharesBought) {
        this.name = name;
        this.budget = budget;
        this.sharesBought = sharesBought;
    }

    public String getName() {
        return name;
    }

    public Float getBudget() {
        return budget;
    }

    public int getSharesBought() {
        return sharesBought;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBudget(Float budget) {
        this.budget = budget;
    }

    public void setSharesBought(int sharesBought) {
        this.sharesBought = sharesBought;
    }
}
