package com.intelligence_1.stockmarketsimulator.model;

import java.util.HashMap;

/**
 *
 * @author guerr
 */
public class Investor {

    private final int investorID;
    private String investorName;
    private int investorNumberOfBoughtShares;
    private HashMap shares;
    private double investorBudget;

    private Investor(InvestorBuilder builder) {
        this.investorID = builder.investorID;
        this.investorName = builder.investorName;
        this.investorNumberOfBoughtShares = builder.investorNumberOfBoughtShares;
        this.investorBudget = builder.investorBudget;
        this.shares = builder.shares;
    }

    public int getInvestorID() {
        return investorID;
    }

    public String getInvestorName() {
        return investorName;
    }

    public void setInvestorName(String investorName) {
        this.investorName = investorName;
    }

    public int getInvestorNumberOfBoughtShares() {
        return investorNumberOfBoughtShares;
    }

    public void setInvestorNumberOfBoughtShares(int investorNumberOfBoughtShares) {
        this.investorNumberOfBoughtShares = investorNumberOfBoughtShares;
    }

    public HashMap getShares() {
        return shares;
    }

    public void setShares(HashMap shares) {
        this.shares = shares;
    }

    public double getInvestorBudget() {
        return investorBudget;
    }

    public void setInvestorBudget(double investorBudget) {
        this.investorBudget = investorBudget;
    }

    @Override
    public String toString() {
        return "ID: " + this.investorID +
                "\n\tName: " + this.investorName +
                "\n\tShares: " + this.investorNumberOfBoughtShares +
                "\n\tTotal: " + this.investorBudget;
    }

    public static class InvestorBuilder {

        private static int auxiliaryID = 0;
        private final int investorID;
        private String investorName;
        private int investorNumberOfBoughtShares;
        private HashMap shares;
        private double investorBudget;

        public InvestorBuilder(String investorName, double investorBudget) {
            this.investorID = auxiliaryID;
            this.investorName = investorName;
            this.investorNumberOfBoughtShares = 0;
            this.investorBudget = investorBudget;
            this.shares = new HashMap();

            auxiliaryID++;
        }

        public Investor build() {
            return new Investor(this);
        }
    }

}

