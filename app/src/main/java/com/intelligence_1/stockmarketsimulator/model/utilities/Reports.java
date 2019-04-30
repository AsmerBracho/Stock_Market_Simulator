/**
 * This Class will Contain a set of method that will return the data used to
 * generate the different report when the simulation is done
 */
package com.intelligence_1.stockmarketsimulator.model.utilities;

import com.intelligence_1.stockmarketsimulator.model.companies.Company;

import java.util.List;

/**
 *
 * @author Asmer Bracho
 */
public class Reports {

    // create an enum for Capital
    public static enum Capital{
        HIGHEST,
        LOWEST;
    }

    /**
     * Get Company with either Highest or Lowest Capital
     * @param companies It takes an array of companies to be filtered
     * @return a company
     */
    public static Company getCompanyCapital(List<Company> companies, Capital capital) {
        // create the companies with the first index
        Company highest = companies.get(0);
        Company lowest = companies.get(0);

        // calculate capital of the first company
        double highestCapital = companies.get(0).getSharePrice()
                * companies.get(0).getCompanyNumberOfShares();
        double lowestCapital = companies.get(0).getSharePrice()
                * companies.get(0).getCompanyNumberOfShares();

        // iterate through the array
        for (int i = 1; i< companies.size(); i++) {
            // temp variable that contains the capital of current (i) company
            // Highest temp
            double tempH = companies.get(i).getSharePrice()
                    * companies.get(i).getCompanyNumberOfShares();

            // lowest temp
            double tempL = companies.get(i).getSharePrice()
                    * companies.get(i).getCompanyNumberOfShares();

            // check if the temp is bigger than the capital
            if (highestCapital < tempH) {
                // swap values
                highestCapital = tempH;
                // this has better value of what we had so assign to your company variable
                highest = companies.get(i);
            }

            // check if temp is lowest than capital
            if (lowestCapital > tempL) {
                // swap values
                lowestCapital = tempL;
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









}
