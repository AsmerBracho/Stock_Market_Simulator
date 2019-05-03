package com.intelligence_1.stockmarketsimulator.model.transactions;

public class Transaction {

    private final int transactionID;
    private static int auxiliaryID = 0;
    private int investorID;
    private int companyID;
    private double sharePrice;

    public Transaction(int investorID, int companyID, double sharePrice){
        this.transactionID = ++auxiliaryID;
        this.investorID = investorID;
        this.companyID = companyID;
        this.sharePrice = sharePrice;
    }

    public static void resetStaticAuxiliaryID() {
        auxiliaryID = 0;
    }

    public int getTransactionID() {
        return transactionID;
    }

    public int getInvestorID() {
        return investorID;
    }

    public int getCompanyID() {
        return companyID;
    }

    public double getSharePrice() {
        return sharePrice;
    }

    @Override
    public String toString(){
        return "Transaction ID: " + transactionID+
                "\n\tInvestor ID: " + investorID+
                "\n\tCompany ID: " + companyID+
                "\n\tShare value: " + sharePrice ;
    }

}
