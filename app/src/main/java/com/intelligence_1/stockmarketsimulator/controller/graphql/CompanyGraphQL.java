package com.intelligence_1.stockmarketsimulator.controller.graphql;

public class CompanyGraphQL {

    private String name;
    private int numShares;
    private int sharesBought;

    public CompanyGraphQL (String name, int numShares, int sharesBought) {
        this.name = name;
        this.numShares = numShares;
        this.sharesBought = sharesBought;
    }

    public String getName() {
        return name;
    }

    public int getNumShares() {
        return numShares;
    }

    public int getSharesBought() {
        return sharesBought;
    }
}