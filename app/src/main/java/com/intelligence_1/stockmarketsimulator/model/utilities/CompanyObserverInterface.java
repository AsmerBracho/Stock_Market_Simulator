package com.intelligence_1.stockmarketsimulator.model.utilities;

import com.intelligence_1.stockmarketsimulator.model.companies.Company;

public interface CompanyObserverInterface {
    /**
     * Method to update objects after a particular event occurs
     */
    public void updateCompaniesList(Company company);
}
