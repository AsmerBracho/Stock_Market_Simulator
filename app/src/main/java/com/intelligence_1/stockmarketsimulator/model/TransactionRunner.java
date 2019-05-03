package com.intelligence_1.stockmarketsimulator.model;

import com.intelligence_1.stockmarketsimulator.model.companies.Company;
import com.intelligence_1.stockmarketsimulator.model.investors.Investor;

public class TransactionRunner {
    private static TransactionRunner instance;

    private TransactionRunner(){}

    public static TransactionRunner getInstance(){
        if(instance == null){
            instance = new TransactionRunner();
        }
        return instance;
    }

    public void doTransaction(Investor investor, Company company) throws Exception{
        if(investor.getInvestorBudget() >= company.getSharePrice() && company.canSellShare()){
            investor.buyShare(company.getCompanyID(), company.getSharePrice());
            company.soldAShare();
        }else if(investor.getInvestorBudget() < company.getSharePrice()){
            throw new Exception("Investor: " + investor.getInvestorName() + " does not have enough funds!");
        }else if(!company.canSellShare()){
            throw new Exception("Company does not have shares to sell");
        }
    }

}
