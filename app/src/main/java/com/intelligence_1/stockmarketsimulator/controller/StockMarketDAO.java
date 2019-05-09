/**
 * Stock Market Project
 * @authors Asmer Bracho (2016328),
 *          Gabriel Oliveira (2016310),
 *          Miguelantonio Guerra (2016324)
 */
package com.intelligence_1.stockmarketsimulator.controller;

import com.apollographql.apollo.ApolloClient;
import okhttp3.OkHttpClient;

/**
 * Class that contain the Apollo Client with the API credential to
 * get the info from
 * Set the BASE_URL whit the url where your API is hosted
 */
public class StockMarketDAO {

    // Url of API
    private static final String BASE_URL = "https://cct-s6-oowdp-sms.herokuapp.com/";
    // This class creates an instance of the Apollo in order to generate a connection
    private static ApolloClient myClient;

    /**
     * Static method that retrieve an apollo client connection
     * @return
     */
    public static ApolloClient getConnection() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();

        myClient = ApolloClient.builder()
                .serverUrl(BASE_URL)
                .okHttpClient(okHttpClient)
                .build();

        return myClient;
    }
}
