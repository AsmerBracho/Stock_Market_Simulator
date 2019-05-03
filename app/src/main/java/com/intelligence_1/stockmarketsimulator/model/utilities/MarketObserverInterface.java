package com.intelligence_1.stockmarketsimulator.model.utilities;


import com.intelligence_1.stockmarketsimulator.model.companies.Company;

import java.util.List;
import java.util.Stack;

/**
 * Stock Market Simulator Project
 *
 * @author Asmer Bracho (2016328), Gabriel Oliveira (2016310), Miguelantonio Guerra (2016324)
 */
public interface MarketObserverInterface {
    /**
     * Method to update objects after a particular event occurs
     */
    public void update(List<Company> companies, Stack<Company> companyStack);


    /**
     * Method to set the Object Subject to be Observed by Observer
     * @param sub Subject Object
     */
    public void setSubject(Subject sub);
}
