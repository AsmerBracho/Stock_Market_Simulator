package com.intelligence_1.stockmarketsimulator.model;


import com.intelligence_1.stockmarketsimulator.model.companies.Company;
import com.intelligence_1.stockmarketsimulator.model.investors.Investor;
import com.intelligence_1.stockmarketsimulator.model.utilities.Reports;
import com.intelligence_1.stockmarketsimulator.model.utilities.SetUpData;

import java.util.List;

public class Test {

    public static void main(String[] args) {
        List<Company> companies = SetUpData.SetUpCompanies(100);
        List<Investor> investors = SetUpData.SetUpInvestors(100);

        System.out.println(Reports.getCompanyByCapital(companies, Reports.Capital.LOWEST));
        System.out.println("---------------");
        System.out.println(Reports.getInvestorByShares(investors, Reports.Shares.LOWEST));
        System.out.println(Reports.getInvestorByShares(investors, Reports.Shares.HIGHEST));
    }
}
