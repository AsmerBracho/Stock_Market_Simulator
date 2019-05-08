/**
 * This Class contains a set of method that returns the data used to
 * generate the different reports when the simulation is done
 */
package com.intelligence_1.stockmarketsimulator.model.utilities;

import com.intelligence_1.stockmarketsimulator.model.companies.Company;
import com.intelligence_1.stockmarketsimulator.model.investors.Investor;

import java.util.ArrayList;
import java.util.List;

/**
 * Stock Market Project
 *
 * @author Asmer Bracho (2016328), Gabriel Oliveira (2016310), Miguelantonio Guerra (2016324)
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

    // create enum for Invested filtering purposes
    public enum Invested {
        MOST,
        LEAST
    }

    /**
     * Get Company with either Highest or Lowest Capital
     *
     * @param companies It takes an array of companies to be filtered
     * @param capital
     * @return a List of companies
     */
    public static List<Company> getCompanyByCapital(List<Company> companies, Capital capital) {
        // Create Lists
        List<Company> listHighest = new ArrayList<>();
        List<Company> listLowest = new ArrayList<>();

        // calculate capital of the first company
        double highestCapital = companies.get(0).getSharePrice()
                * companies.get(0).getCompanyNumberOfShares();

        // to start with lowest = highest
        double lowestCapital = highestCapital;

        // add your companies to the list
        listHighest.add(companies.get(0));
        listLowest.add(companies.get(0));

        // iterate through the array
        for (int i = 1; i < companies.size(); i++) {
            // temp variable that contains the capital of current (i) company
            double temp = companies.get(i).getSharePrice()
                    * companies.get(i).getCompanyNumberOfShares();

            //----------------------COMPANY WITH THE HIGHEST CAPITAL ------------//

            // Now we need to checked if the value is equals to the temp
            if (highestCapital == temp) { // then =>
                // we should put this value into the array as well
                listHighest.add(companies.get(i));
                // check if the temp is bigger than the capital
            } else if (highestCapital < temp) {
                // swap values
                highestCapital = temp;

                // put the record in the list
                listHighest.clear();
                listHighest.add(companies.get(i));
            }

            //----------------------COMPANY WITH THE LOWEST CAPITAL ------------//

            // Now we need to checked if the lowest capital is equals to the temp
            if (lowestCapital == temp) { // then =>
                // we should put this value into the array as well
                listLowest.add(companies.get(i));
                // check if temp is lowest than capital
            } else if (lowestCapital > temp) {
                // swap values
                lowestCapital = temp;
                // assign to our company variable
                listLowest.clear();
                listLowest.add(companies.get(i));
            }
        }

        // finally return the company(s) requested in parameters
        if (capital.equals(Capital.HIGHEST)) {
            return listHighest;
        } else if (capital.equals(Capital.LOWEST)) {
            return listLowest;
        }
        return null;
    }

    /**
     * Get Company with either Highest or Lowest Capital
     *
     * @param investors
     * @param shares
     * @return a List of Investors
     */
    public static List<Investor> getInvestorByShares(List<Investor> investors, Shares shares) {
        // Create Lists
        List<Investor> listHighest = new ArrayList<>();
        List<Investor> listLowest = new ArrayList<>();

        // get the number of shares for highest and lowest
        int numberOfSharesH = investors.get(0).getInvestorNumberOfBoughtShares();
        int numberOfSharesL = numberOfSharesH;

        // add your investors to the list
        listHighest.add(investors.get(0));
        listLowest.add(investors.get(0));

        for (int i = 1; i < investors.size(); i++) {
            // create temp variable
            int temp = investors.get(i).getInvestorNumberOfBoughtShares();

            //----------------------INVESTOR WITH THE HIGHEST NUMBER OF SHARES ------------//

            // Now we need to checked if the value is equals to the temp
            if (numberOfSharesH == temp) { // then =>
                // we should put this value into the list
                listHighest.add(investors.get(i));
                // check if the temp is bigger than sharesH
            } else if (numberOfSharesH < temp) {
                // swap values
                numberOfSharesH = temp;
                // clear your list and add the new one
                listHighest.clear();
                listHighest.add(investors.get(i));
            }

            //----------------------INVESTOR WITH THE LOWEST NUMBER OF SHARES ------------//

            if (numberOfSharesL == temp) {
                // put the value into the list
                listLowest.add(investors.get(i));

                // else check if temp is lowest than sharesL
            } else if (numberOfSharesL > temp) {
                // swap values
                numberOfSharesL = temp;
                // clear your list and add the new one
                listLowest.clear();
                listLowest.add(investors.get(i));
            }
        }

        // finally return the company requested in parameters
        if (shares.equals(Shares.HIGHEST)) {
            return listHighest;
        } else if (shares.equals(Shares.LOWEST)) {
            return listLowest;
        }
        return null;
    }


    /**
     * This method takes a list of investor and return the investor(s) that have invested
     * in the most/least companies
     *
     * @param investors
     * @param invested
     * @return
     */
    public static List<Investor> getInvestorByCompanyInvested(List<Investor> investors, Invested invested) {
        // create the Lists
        List<Investor> investedMost = new ArrayList<>();
        List<Investor> investedLess = new ArrayList<>();

        // Initially we need to hold the value of the first investor
        // The logic is the bigger our list with shares is the better and vice versa
        int most = investors.get(0).getShares().size();
        int less = investors.get(0).getShares().size();

        // put the first investor in both lists (In case the first investor is already your best result)
        investedMost.add(investors.get(0));
        investedLess.add(investors.get(0));

        for (int i = 1; i < investors.size(); i++) {
            // create temp variable
            int temp = investors.get(i).getShares().size();

            //----------------------INVESTOR THAT INVESTED IN THE MOST COMPANIES ------------//

            // if the value in temp is equals to your variable most
            if (most == temp) { //=> then put the investor in the list
                investedMost.add(investors.get(i));

                // else check if the temp is bigger than your current
            } else if (most < temp) {
                // swap values
                most = temp;
                // clear your list and add the new investor to the list
                investedMost.clear();
                investedMost.add(investors.get(i));
            }

            //----------------------INVESTOR THAT INVESTED IN THE LESS COMPANIES ------------//

            // if the value in temp is equals to your variable less
            if (less == temp) { //=> then put the investor in the list
                investedLess.add(investors.get(i));

                // else check if the temp is smaller than your current
            } else if (less > temp) { // if true => then
                // swap values
                less = temp;
                // clear your list and add the new investor to the list
                investedLess.clear();
                investedLess.add(investors.get(i));
            }
        }

        // Finally return either the Most or the Less according to your parameter request
        if (invested.equals(Invested.MOST)) {
            return investedMost;
        } else if (invested.equals(Invested.LEAST)) {
            return investedLess;
        }
        return null;
    }
}
