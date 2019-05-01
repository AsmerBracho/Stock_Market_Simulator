/**
 * Interface: Observer
 * 
 * Purpose: To define some standard functionality between observers
 */
package com.intelligence_1.stockmarketsimulator.model.companies;

/**
 * Stock Market Simulator Project
 * 
 * @author Asmer Bracho (2016328), Gabriel Oliveira (2016310), Miguelantonio Guerra (2016324)
 */
public interface Observer {
	
    /**
     * Method to update objects after a particular event occurs
     */
    public void update();

    /**
     * Method to set the Object Subject to be Observed by Observer
     * @param sub Subject Object
     */
    public void setSubject(Subject sub);   
}
