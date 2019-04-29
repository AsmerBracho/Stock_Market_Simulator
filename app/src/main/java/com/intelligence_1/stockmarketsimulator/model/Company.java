package com.intelligence_1.stockmarketsimulator.model;

import java.util.List;

/**
 *
 * @author guerr
 */
public class Company {
    private final int companyID;
    private String companyName;
    private int companyNumberOfShares;
    private double sharePrice;
    private int sharesSold;

    private Company(CompanyBuilder builder) {
        this.companyID = builder.companyID;
        this.companyName = builder.companyName;
        this.companyNumberOfShares = builder.companyNumberOfShares;
        this.sharePrice = builder.sharePrice;
        this.sharesSold = builder.sharesSold;
    }

    public void soldAShare() {
        this.sharesSold++;
    }

    public void raiseSharePrice() {
        this.sharePrice = 2*this.sharePrice;
    }

    public void dropSharePrice() {
        this.sharePrice = 0.98*this.sharePrice;
    }

    @Override
    public String toString() {
        return "ID: " + companyID +
                "\n\tName: " + companyName +
                "\n\tShares: " + companyNumberOfShares +
                "\n\tPrice: " + sharePrice +
                "\n\tSold: " + sharesSold;
    }

    public static class CompanyBuilder {

        private static int auxiliaryID = 0;
        private final int companyID;
        private String companyName;
        private int companyNumberOfShares;
        private double sharePrice;
        private int sharesSold;

        public CompanyBuilder(String companyName, int companyNumberOfShares, double sharePrice) {
            this.companyID = auxiliaryID;
            this.companyName = companyName;
            this.companyNumberOfShares = companyNumberOfShares;
            this.sharePrice = sharePrice;
            this.sharesSold = 0;

            auxiliaryID++;
        }

        public Company build() {
            return new Company(this);
        }
    }



}

