/**
 * Class: CompanyObserver
 * 
 * Purpose: Concrete class that defines the observers for the Company Objects
 */
package com.intelligence_1.stockmarketsimulator.model.companies;

import java.util.List;

/**
 * Stock Market Simulator Project
 * 
 * @author Asmer Bracho (2016328), Gabriel Oliveira (2016310), Miguelantonio Guerra (2016324)
 */
public class CompanyObserver implements Observer {
    
    private List<Subject> companies; // List of companies to be observed
    
    /**
     * Constructor for the Concrete Observer Class.
     * 
     * It takes the parameters:
     * 
     * @param companies a list of Company Objects
     */
    public CompanyObserver(List<Subject> companies) {
        this.companies = companies; // Companies to be observed
        
        // Here we register the observer to the 
        for (Subject c : companies) {
            c.register(this);
        }
        
    }

    @Override
    public void update() {
        for (Subject c : companies) {
            if (((Company)c).getSharesSold() == 0) {
                ((Company)c).raiseSharePrice();
            }
        }
    }

    @Override
    public void setSubject(Subject sub) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    

}
