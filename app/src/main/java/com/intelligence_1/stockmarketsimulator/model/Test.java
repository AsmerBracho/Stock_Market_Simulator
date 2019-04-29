package com.intelligence_1.stockmarketsimulator.model;

import java.util.List;

public class Test {

    public static void main(String[] args) {
        List<Company> companies = SetUpData.SetUpCompanies(100);
        List<Investor> investors = SetUpData.SetUpInvestors(100);

        System.out.println(Reports.getCompanyCapital(companies, Reports.Capital.HIGHEST));
        System.out.println("--------");

        System.out.println(Reports.getCompanyCapital(companies, Reports.Capital.LOWEST));

    }
}
