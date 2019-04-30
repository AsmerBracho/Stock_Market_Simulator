/**
 * Class: Company
 *
 * Purpose: Create instances of Companies using the Builder Design Pattern
 */

package com.intelligence_1.stockmarketsimulator.model.companies;

/**
 * Stock Market Simulator Project
 *
 * @author Asmer Bracho (2016328), Gabriel Oliveira (2016310), Miguelantonio Guerra (2016324)
 */
public class Company {
    private final int companyID; // Company ID
    private String companyName; // Company Name
    private int companyNumberOfShares; // Company Number Of Shares
    private double sharePrice; // Price of each Share
    private int sharesSold; // How many Shares the Company has sold

    /**
     * This is the Constructor of the Company.
     * It is declare private because the class is instantiated using the builder pattern.
     *
     * It takes the Company Builder as a parameter
     * @param builder CompanyBuilder Object
     */
    private Company(CompanyBuilder builder) {
        this.companyID = builder.companyID;
        this.companyName = builder.companyName;
        this.companyNumberOfShares = builder.companyNumberOfShares;
        this.sharePrice = builder.sharePrice;
        this.sharesSold = builder.sharesSold;
    }

    /**
     * This method is called every time the company sells a share.
     */
    public void soldAShare() {
        this.sharesSold++;
    }

    /**
     * This method is called every time the company sells 10 shares.
     * The price of the shares double up.
     */
    public void raiseSharePrice() {
        this.sharePrice = 2*this.sharePrice;
    }

    /**
     * This method is called every time any 10 shares are sold and this company has not sold any.
     * The price of the shares goes down by 2%
     */
    public void dropSharePrice() {
        this.sharePrice = 0.98*this.sharePrice;
    }

    /**
     * Getter for the company ID
     * @return the company ID
     */
    public int getCompanyID() {
        return companyID;
    }

    /**
     * Getter for the company name
     * @return the company name
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * Getter for the company nymber of shares
     * @return company number of shares
     */
    public int getCompanyNumberOfShares() {
        return companyNumberOfShares;
    }

    /**
     * Getter for the shares price
     * @return shares price
     */
    public double getSharePrice() {
        return sharePrice;
    }

    /**
     * Setter for the shares price
     * @param sharePrice share price
     */
    public void setSharePrice(double sharePrice) {
        this.sharePrice = sharePrice;
    }

    /**
     * Getter for number of shares sold
     * @return number of shares sold
     */
    public int getSharesSold() {
        return sharesSold;
    }

    /**
     * Setter for shares sold
     * @param sharesSold shares sold
     */
    public void setSharesSold(int sharesSold) {
        this.sharesSold = sharesSold;
    }

    /**
     * To string method used for printing companies in the console.
     * @return Company String
     */
    @Override
    public String toString() {
        return "ID: " + companyID +
                "\n\tName: " + companyName +
                "\n\tShares: " + companyNumberOfShares +
                "\n\tPrice: " + sharePrice +
                "\n\tSold: " + sharesSold;
    }

    /**
     * Public Static Inner Class. This is the Builder Class to instantiate the main class.
     * It has the same attributes as the main class.
     */
    public static class CompanyBuilder {

        private static int auxiliaryID = 0; // Auxiliary value to calculate company builder ID
        private final int companyID; // company builder ID
        private String companyName; // company builder name
        private int companyNumberOfShares; // company builder number of shares
        private double sharePrice; // company builder shares price
        private int sharesSold; // company builder shares sold

        /**
         * Constructor for the inner static class, the builder class
         * @param companyName company builder name
         * @param companyNumberOfShares company builder number of shares
         * @param sharePrice company builder shares price
         */
        public CompanyBuilder(String companyName, int companyNumberOfShares, double sharePrice) {
            this.companyID = auxiliaryID;
            this.companyName = companyName;
            this.companyNumberOfShares = companyNumberOfShares;
            this.sharePrice = sharePrice;
            this.sharesSold = 0; // At the beginning the company has not sold any shares

            auxiliaryID++;
        }

        /**
         * Builder Method to build the Company Object
         * @return Company Object
         */
        public Company build() {
            return new Company(this);
        }
    }

}

