package com.intelligence_1.stockmarketsimulator;

import com.intelligence_1.stockmarketsimulator.model.companies.Company;
import com.intelligence_1.stockmarketsimulator.model.utilities.MarketObserverInterface;
import com.intelligence_1.stockmarketsimulator.model.utilities.Subject;

import java.util.List;
import java.util.Stack;

public class MarketObserver implements MarketObserverInterface {

    private Subject market;
    private List<Company> companies;

    public MarketObserver(Subject market) {
        this.market = market; // Companies to be observed
        market.register(this);
    }

    @Override
    public void update(List<Company> companies, Stack<Company> companyStack) {
        // TC WC=O(n.m)
        if (!companies.isEmpty()) {
            for (Company c : companies) {
                if (!companyStack.contains(c)) {
                    c.dropSharePrice();
                }
            }
        }
    }

    @Override
    public void setSubject(Subject sub) {

    }
}
