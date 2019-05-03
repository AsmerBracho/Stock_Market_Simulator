/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intelligence_1.stockmarketsimulator;

/**
 *
 * @author guerr
 */
public class StockMarketSimulator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Market market = new Market();
        new MarketObserver(market);
        market.trade();
        market.listAllCompanies();
    }
}
