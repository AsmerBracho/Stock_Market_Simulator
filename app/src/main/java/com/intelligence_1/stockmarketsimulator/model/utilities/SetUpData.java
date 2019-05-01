/**
 * Public Class SetUpData in charge of creating the instances of Investors and Companies
 * that we will use to implement in the Simulator
 * <p>
 * This class will create Investor Companies and Shares with
 * a Builder Pattern Design
 */

package com.intelligence_1.stockmarketsimulator.model.utilities;

import com.intelligence_1.stockmarketsimulator.model.companies.Company;
import com.intelligence_1.stockmarketsimulator.model.investors.Investor;

import java.util.ArrayList;
import java.util.List;

import io.bloco.faker.Faker;


/**
 * Stock Market Project
 * @author Asmer Bracho (2016328), Gabriel Oliveira (2016310), Miguelantonio Guerra (2016324)
 */
public class SetUpData {

    /**
     * We will use the https://jitpack.io library to generated
     * the fake Random data for our program
     */
    private static Faker faker = new Faker();

    /**
     * Method that build a random number of Companies
     * @param numberOfCompanies to create
     * @return a list of n Companies
     */
    public static List<Company> SetUpCompanies(int numberOfCompanies) {
        // create List of companies
        List<Company> companies = new ArrayList<>();

        //Create a n number of companies
        for (int i = 0; i < numberOfCompanies; i++) {
            // create a fake name
            String name = faker.company.name();

            // info for shares
            int numberOfShares = (int) (Math.random() * 900 + 100);
            double price = (double) (Math.random() * 90 + 10);

            // Add the company name and shares to the List
            companies.add(new Company.CompanyBuilder(name, numberOfShares, price).build());

        }

        return companies;
    }

    /**
     * Method that build a random number of Investors
     * @param numberOfInvestors to create
     * @return a List of n Companies
     */
    public static List<Investor> SetUpInvestors(int numberOfInvestors) {
        // create List of companies
        List<Investor> investors = new ArrayList<>();

        //Create a n number of investors
        for (int i = 0; i < numberOfInvestors; i++) {
            // create a fake name
            String name = faker.name.firstName() + " " + faker.name.lastName();
            // create a budget between 1000 and 10000
            double budget = (double) (Math.random() * 9000 + 1000);

            // add Investor to the list
            investors.add(new Investor.InvestorBuilder(name, budget).build());
        }

        return investors;
    }


}
