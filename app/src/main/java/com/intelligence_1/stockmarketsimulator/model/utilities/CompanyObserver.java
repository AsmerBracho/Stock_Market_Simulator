package com.intelligence_1.stockmarketsimulator.model.utilities;

import com.intelligence_1.stockmarketsimulator.Market;
import com.intelligence_1.stockmarketsimulator.model.companies.Company;

import java.util.List;

public class CompanyObserver implements CompanyObserverInterface {
    private Market market;

    public CompanyObserver(Market market) {
        this.market = market;
        for (Subject c : market.getCompanies()) {
            c.register(this);
        }
    }

    @Override
    public void updateCompaniesList(Company company) {
        market.updateCompaniesList(company);
    }
}
