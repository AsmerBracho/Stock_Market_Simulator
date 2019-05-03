/**
 * Interface: Subject
 * 
 * Purpose: To define some standard functionality between objects to be observed
 */
package com.intelligence_1.stockmarketsimulator.model.utilities;

import com.intelligence_1.stockmarketsimulator.MarketObserver;
import com.intelligence_1.stockmarketsimulator.model.investors.Investor;

/**
 * Stock Market Simulator Project
 * 
 * @author Asmer Bracho (2016328), Gabriel Oliveira (2016310), Miguelantonio Guerra (2016324)
 */
public interface Subject<T> {

    /**
     * Method to register observers to the Subject Object
     * @param obj Observer Object
     */
    public void register(T obj);


    /**
     * Method to unregister observers from the Subject Object
     */
    public void unregister();

    /**
     * Method to notify all observers that something happen to the subjects
     */
    public void notifyObservers();

    /**
     * Method to return the update that has been done in the Subject Object
     * @return 
     */
    public Object getUpdate();
    
}
