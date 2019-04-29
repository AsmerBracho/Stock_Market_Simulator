/**
 * This Class will Contain a set of method that will return the data used to
 * generate the different report when the simulation is done
 */
package com.intelligence_1.stockmarketsimulator.model;

import java.util.List;

/**
 * @author Asmer Bracho
 */
public class Reports {

    // create an enum for Capital
    public enum Capital {
        HIGHEST,
        LOWEST;
    }

    // create an enum for Shares
    public enum Shares {
        HIGHEST,
        LOWEST;
    }

    /**
     * Get Company with either Highest or Lowest Capital
     *
     * @param companies It takes an array of companies to be filtered
     * @return a company
     */
    public static Company getCompanyByCapital(List<Company> companies, Capital capital) {
        // create the companies with the first index
        Company highest = companies.get(0);
        Company lowest = companies.get(0);

        // calculate capital of the first company
        double highestCapital = companies.get(0).getSharePrice()
                * companies.get(0).getCompanyNumberOfShares();

        // to start with lowest = highest
        double lowestCapital = highestCapital;

        // iterate through the array
        for (int i = 1; i < companies.size(); i++) {
            // temp variable that contains the capital of current (i) company

            double temp = companies.get(i).getSharePrice()
                    * companies.get(i).getCompanyNumberOfShares();

            // check if the temp is bigger than the capital
            if (highestCapital < temp) {
                // swap values
                highestCapital = temp;
                // this has better value of what we had so assign to your company variable
                highest = companies.get(i);
            }

            // check if temp is lowest than capital
            if (lowestCapital > temp) {
                // swap values
                lowestCapital = temp;
                // assign to our company variable
                lowest = companies.get(i);
            }
        }

        // finally return the company requested in parameters
        if (capital.equals(Capital.HIGHEST)) {
            return highest;
        } else if (capital.equals(Capital.LOWEST)) {
            return lowest;
        }
        return null;
    }

    /**
     * @param investors
     * @param shares
     * @return
     */
    public static Investor getInvestorByShares(List<Investor> investors, Shares shares) {

        // create the Investors with the first index
        Investor highest = investors.get(0);
        Investor lowest = investors.get(0);

        // get the number of shares for highest and lowest
        int numberOfSharesH = investors.get(0).getInvestorNumberOfBoughtShares();
        int numberOfSharesL = numberOfSharesH;

        for (int i = 1; i < investors.size(); i++) {
            // create temp variable
            int temp = investors.get(i).getInvestorNumberOfBoughtShares();

            // check if the temp is bigger than sharesH
            if (numberOfSharesH < temp) {
                // swap values
                numberOfSharesH = temp;
                // this has better value of what we had so assign it to your investor variable
                highest = investors.get(i);
            }

            // check if temp is lowest than sharesL
            if (numberOfSharesL > temp) {
                // swap values
                numberOfSharesL = temp;
                // assign to our investor variable
                lowest = investors.get(i);
            }

            // finally return the company requested in parameters
            if (shares.equals(Shares.HIGHEST)) {
                return highest;
            } else if (shares.equals(Shares.LOWEST)) {
                return lowest;
            }
        }
        return null;
    }


}
