package com.intelligence_1.stockmarketsimulator.model.utilities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.intelligence_1.stockmarketsimulator.model.companies.Company;
import com.intelligence_1.stockmarketsimulator.model.investors.Investor;
import io.bloco.faker.Faker;


/**
 * Public Class SetUpData in charge of creating the instances of Investors and Companies
 * that we will use to implement in the Simulator
 *
 * This class will create Investor Companies and Shares with
 * a Builder Pattern Design
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
        Random random = new Random();

        //Create a n number of companies
        for (int i = 0; i < numberOfCompanies; i++) {
            // create a fake name
            String name = faker.company.name();

            int minShares = 500;
            int maxShares = 1000;

            double minPrice = 10;
            double maxPrice = 100;

//            int numberOfShares = (int) (Math.random() * 500) + 500;
//            int numberOfShares = random.nextInt((maxShares - minShares ) + 1) + minShares;
            int numberOfShares = random.nextInt((maxShares - minShares ) + 1) + minShares;
//            double price = random.nextDouble((maxPrice - minPrice) + 1) + minPrice;
            double price = (Math.random() * ((maxPrice - minPrice) + 1)) + minPrice;

            // Add the company name and shares to the List
            companies.add(new Company.CompanyBuilder(name, numberOfShares, price).build());

        }
        int sum = 0;
        for (Company c : companies) {
            sum += c.getCompanyNumberOfShares();
        }

        System.out.println("c.getCompanyNumberOfShares(): " + sum);
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

            double minBudget = 1000;
            double maxBudget = 10000;
            // create a budget between 1000 and 10000
//            double budget = (double) (Math.random() * 9000) + 1000;
            double budget  = (int)(Math.random() * ((maxBudget - minBudget) + 1)) + minBudget;

            // add Investor to the list
            investors.add(new Investor.InvestorBuilder(name, budget).build());
        }

        return investors;
    }


}
