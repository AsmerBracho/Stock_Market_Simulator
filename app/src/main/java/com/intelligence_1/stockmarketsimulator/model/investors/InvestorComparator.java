package com.intelligence_1.stockmarketsimulator.model.investors;

import java.util.Comparator;

public class InvestorComparator implements Comparator<Investor> {

    @Override
    public int compare(Investor investor1, Investor investor2) {
        return investor1.getInvestorBudget().compareTo(investor2.getInvestorBudget());
    }
}
