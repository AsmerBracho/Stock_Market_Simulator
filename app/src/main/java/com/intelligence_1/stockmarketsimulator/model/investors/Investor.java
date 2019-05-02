/**
 * Class: Investor
 *
 * Purpose: To create instances of Investors by using the Builder Pattern.
 */
package com.intelligence_1.stockmarketsimulator.model.investors;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.HashMap;

/**
 * Stock Market Project
 * @author Asmer Bracho (2016328), Gabriel Oliveira (2016310), Miguelantonio Guerra (2016324)
 */
public class Investor implements Parcelable {
    private final int investorID; // Investor ID
    private String investorName; // Investor Name
    private int investorNumberOfBoughtShares; // Investor number of shares
    private HashMap shares; // Shares that the Investors have bought
    private double investorBudget; // Investor Budget

    /**
     * This is the Constructor of the Investor.
     * It is declare private because the class is instantiated using the builder pattern.
     *
     * It takes the Investor Builder as a parameter
     * @param builder InvestorBuilder Object
     */
    private Investor(InvestorBuilder builder) {
        this.investorID = builder.investorID;
        this.investorName = builder.investorName;
        this.investorNumberOfBoughtShares = builder.investorNumberOfBoughtShares;
        this.investorBudget = builder.investorBudget;
        this.shares = builder.shares;
    }

    protected Investor(Parcel in) {
        investorID = in.readInt();
        investorName = in.readString();
        investorNumberOfBoughtShares = in.readInt();
        investorBudget = in.readDouble();
        shares = in.readHashMap(HashMap.class.getClassLoader());
    }

    public static final Creator<Investor> CREATOR = new Creator<Investor>() {
        @Override
        public Investor createFromParcel(Parcel in) {
            return new Investor(in);
        }

        @Override
        public Investor[] newArray(int size) {
            return new Investor[size];
        }
    };

    /**
     * Getter for the investor ID
     * @return investor ID
     */
    public int getInvestorID() {
        return investorID;
    }

    /**
     * Getter for the Investor Name
     * @return investor name
     */
    public String getInvestorName() {
        return investorName;
    }

    /**
     * Setter for the investor name
     * @param investorName investor name
     */
    public void setInvestorName(String investorName) {
        this.investorName = investorName;
    }

    /**
     * Getter for the investor number of bought shares
     * @return investor number of bought shares
     */
    public int getInvestorNumberOfBoughtShares() {
        return investorNumberOfBoughtShares;
    }

    /**
     * Setter for the investor number of bought shares
     * @param investorNumberOfBoughtShares investor number of bought shares
     */
    public void setInvestorNumberOfBoughtShares(int investorNumberOfBoughtShares) {
        this.investorNumberOfBoughtShares = investorNumberOfBoughtShares;
    }

    /**
     * Getter for the Map of companies the investor has bought from (key) and how many have bought from them (value)
     * @return shares the investors have bought from companies.
     */
    public HashMap getShares() {
        return shares;
    }



    /**
     * Setter for the Map of companies the investor has bought from (key) and how many have bought from them (value)
     * @param shares shares the investors have bought from companies.
     */
    public void setShares(HashMap shares) {
        this.shares = shares;
    }

    /**
     * Getter for the investor budget
     * @return investor budget
     */
    public double getInvestorBudget() {
        return investorBudget;
    }

    /**
     * Setter for the investor budget
     * @param investorBudget investor budget
     */
    public void setInvestorBudget(double investorBudget) {
        this.investorBudget = investorBudget;
    }

    /**
     * To string method used for printing companies in the console.
     * @return Investor String
     */
    @Override
    public String toString() {
        return "ID: " + this.investorID +
                "\n\tName: " + this.investorName +
                "\n\tShares: " + this.investorNumberOfBoughtShares +
                "\n\tTotal: " + this.investorBudget;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeInt(investorID);
        dest.writeString(investorName);
        dest.writeInt(investorNumberOfBoughtShares);
        dest.writeDouble(investorBudget);
        dest.writeMap(shares);
    }

    /**
     * Public Static Inner Class. This is the Builder Class to instantiate the main class.
     * It has the same attributes as the main class.
     */
    public static class InvestorBuilder {

        private static int auxiliaryID = 0; // // Auxiliary value to calculate investor builder ID
        private final int investorID; // investor builder ID
        private String investorName; // investor builder name
        private int investorNumberOfBoughtShares; // investor builder number of bought shares
        private HashMap<Integer, Integer> shares; // investor builder shares bought from companies
        private double investorBudget; // investor builder budget

        /**
         * Constructor for the inner static class, the builder class
         * @param investorName investor builder name
         * @param investorBudget investor builder budget
         */
        public InvestorBuilder(String investorName, double investorBudget) {
            this.investorID = auxiliaryID;
            this.investorName = investorName;
            this.investorNumberOfBoughtShares = 0; // Number of bought shares are 0 at the beginning of the program
            this.investorBudget = investorBudget;
            this.shares = new HashMap<Integer, Integer>();

            auxiliaryID++;
        }

        /**
         * Builder Method use to instantiated the Main Class
         * @return Investor Object
         */
        public Investor build() {
            return new Investor(this);
        }
    }

}

