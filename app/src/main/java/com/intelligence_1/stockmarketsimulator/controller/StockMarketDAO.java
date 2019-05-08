package com.intelligence_1.stockmarketsimulator.controller;

import com.apollographql.apollo.ApolloClient;
import okhttp3.OkHttpClient;

/**
 * Class that contain the Apollo Client with the API credential to
 * get the info from
 *
 * Set the BASE_URL whit the url where your API is hosted
 */
public class StockMarketDAO {

    private static final String BASE_URL = "http://192.168.0.144:8888/api";
    private static ApolloClient myClient;

    public static ApolloClient getMyClient() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();

        myClient = ApolloClient.builder()
                .serverUrl(BASE_URL)
                .okHttpClient(okHttpClient)
                .build();

        return myClient;

    }
}
