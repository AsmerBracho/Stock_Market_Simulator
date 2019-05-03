package com.intelligence_1.stockmarketsimulator;

import com.intelligence_1.stockmarketsimulator.model.TransactionRunner;
import com.intelligence_1.stockmarketsimulator.model.companies.Company;
import com.intelligence_1.stockmarketsimulator.model.investors.Investor;
import com.intelligence_1.stockmarketsimulator.model.transactions.Transaction;

public class TransactionManager{

    private Investor investor;
    private Company company;
    private Market market;

    public static Transaction execute(Investor investor, Company company, Market market){
        try {
            TransactionRunner.getInstance().doTransaction(investor, company);
            market.addCompanyToStack(company);
            return new Transaction(investor.getInvestorID(), company.getCompanyID(), company.getSharePrice());
        }catch (Exception e){
//            System.out.println("TransactionRunner did not happen: " );
        }
        return null;
    }

}
