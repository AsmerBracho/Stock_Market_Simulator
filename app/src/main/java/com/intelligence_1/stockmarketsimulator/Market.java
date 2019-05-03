package com.intelligence_1.stockmarketsimulator;

import com.intelligence_1.stockmarketsimulator.model.TransactionRunner;
import com.intelligence_1.stockmarketsimulator.model.companies.Company;
import com.intelligence_1.stockmarketsimulator.model.companies.CompanyComparator;
import com.intelligence_1.stockmarketsimulator.model.investors.Investor;
import com.intelligence_1.stockmarketsimulator.model.investors.InvestorComparator;
import com.intelligence_1.stockmarketsimulator.model.transactions.Transaction;
import com.intelligence_1.stockmarketsimulator.model.utilities.CompanyObserver;
import com.intelligence_1.stockmarketsimulator.model.utilities.MarketObserverInterface;
import com.intelligence_1.stockmarketsimulator.model.utilities.SetUpData;
import com.intelligence_1.stockmarketsimulator.model.utilities.Subject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class Market implements Subject<MarketObserverInterface> {

    private Stack<Company> companiesStack;

    private List<Company> companies = SetUpData.SetUpCompanies(100);
    private List<Company> inactiveCompanies;
    private List<Investor> investors = SetUpData.SetUpInvestors(100);
    private MarketObserverInterface observer;
    private List<Transaction> transactionsHistory;
    private int counter = 0;

    public  Market(){
        /*for(Company c : companies){
            System.out.println("Company number of shares: " + c.getCompanyNumberOfShares());
        }*/
//        System.out.println("Share price: " + companies.get(0).getSharePrice());
        transactionsHistory = new ArrayList<Transaction>();
        observer = null;
        companiesStack = new Stack<>();
        inactiveCompanies = new ArrayList<Company>();
        new CompanyObserver(this);
    }

    public void trade(){
        try{
            while (companies.size() > 0 && this.canTrade(companies, investors)){

//                if (companies.size() == 0) {
//                    break;
//                } else {
//                    if (!this.canTrade()) {
//                        break;
//                    }
//                }

//            for (int i = 0; i < 9500; i ++){
                int randomInvestorIndex = (int)(Math.random() * investors.size());

                Investor investor = investors.get(randomInvestorIndex);
                Company randomCompany = companies.get(investor.pickACompany(companies.size()));
                Transaction transaction = TransactionManager.execute(investor, randomCompany, this);
                if(transaction != null){
                    transactionsHistory.add(transaction);
                    counter = transactionsHistory.size();
                }
            }

        }catch (Exception e){
            System.out.println("Interrupted2: ");
            System.out.println(e.getStackTrace()[0]);
            System.out.println(e.getStackTrace()[1]);
            System.out.println(e.getStackTrace()[2]);
            System.out.println(e.getStackTrace()[3]);
            System.out.println(e.getStackTrace()[4]);
            System.out.println(e.getStackTrace().length);
        }
    }

    public void updateCompaniesList(Company company) {
        if(!companies.isEmpty()){
            this.companies.remove(company);
            this.addInactiveCompany(company);
            System.out.println("updateCompaniesList");
            System.out.println("companies.size: " + companies.size());
        }else{
            System.out.println("Companies is not empty");
        }
    }

    public void listAllCompanies(){

        inactiveCompanies.addAll(companies);

        // Reset the AuxiliaryID for Transaction
        Transaction.resetStaticAuxiliaryID();
//
//        System.out.println("Investor with best budget: " + getInvestorWithHighestBudget(investors).getInvestorName() + "\n\t" + getInvestorWithHighestBudget(investors).getInvestorBudget());
//        System.out.println("Company with worst share: " + getCompanyWithCheapestShare(inactiveCompanies));
//        System.out.println("Company with best share: " + getCompanyWithMostExpensiveShare(inactiveCompanies));
//
//
//        System.out.println("Transactions DONER: " + counter);
//
//        int sum = 0;
//        for (Company c : inactiveCompanies) {
//            sum += c.getSharesSold();
//        }
//        System.out.println("Shares sold: " + sum);
//
//        double avgPriceOfTransactions = 0;
//        for(Transaction t : transactionsHistory){
////            System.out.println(t);
//            avgPriceOfTransactions += t.getSharePrice();
//        }
//        System.out.println("avgPriceOfTransactions: " + avgPriceOfTransactions / transactionsHistory.size());
//        System.out.println("Total cost of transactions: " + avgPriceOfTransactions);
    }

    public void addInactiveCompany(Company inactiveCompany) {
//        inactiveCompany.unregister();
        System.out.println(inactiveCompany.getCompanyName() + "Share before adding it to inactives:\n\t" + inactiveCompany.getSharePrice());
        this.inactiveCompanies.add(inactiveCompany);
    }

    public void addCompanyToStack(Company company){
        if(this.companiesStack.size() == 10){
            notifyObservers();
            this.companiesStack.clear();
        }
        this.companiesStack.add(company);
    }

    private Company getCompanyWithMostExpensiveShare(List<Company> companies){
        if(companies.size() > 0){
            return Collections.max(companies, new CompanyComparator());
        }
        return null;
    }

    private Company getCompanyWithCheapestShare(List<Company> companies){
        if(companies.size() > 0){
            return Collections.min(companies, new CompanyComparator());
        }
        return null;
    }

    private Investor getInvestorWithHighestBudget(List<Investor> investors){
        if(investors.size() > 0){
            return Collections.max(investors, new InvestorComparator());
        }
        return null;
    }

    private boolean canTrade(List<Company> companies, List<Investor> investors) {
        return this.getInvestorWithHighestBudget(investors).getInvestorBudget() > this.getCompanyWithCheapestShare(companies).getSharePrice();
    }

    @Override
    public void register(MarketObserverInterface observer) {
        this.observer = observer;
    }

    @Override
    public void unregister() {
        this.observer = null;
    }

    @Override
    public void notifyObservers() {
        this.observer.update(this.companies, this.companiesStack);
    }

    @Override
    public Object getUpdate() {
        return null;
    }

    public List<Company> getCompanies() {
        return companies;
    }

    public List<Company> getInactiveCompanies() { return inactiveCompanies; }

    public void setInactiveCompanies(List<Company> inactiveCompanies) {
        this.inactiveCompanies = inactiveCompanies;
    }

    public List<Investor> getInvestors() { return investors;}
}
