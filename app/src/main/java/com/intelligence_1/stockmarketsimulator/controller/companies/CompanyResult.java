/**
 * Stock Market Project
 *
 * @authors Asmer Bracho (2016328),
 * Gabriel Oliveira (2016310),
 * Miguelantonio Guerra (2016324)
 */
package com.intelligence_1.stockmarketsimulator.controller.companies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.intelligence_1.stockmarketsimulator.R;
import com.intelligence_1.stockmarketsimulator.model.companies.Company;

import java.util.ArrayList;
import java.util.List;

/**
 * Class Company Result
 * It contains the cards for the companies results for the different records
 */
public class CompanyResult extends AppCompatActivity {

    private RecyclerView listOfCompanies; // Views for the list of companies
    private List<Company> companies;
    private AdapterCompanyResult adapterCompanyResult;
    private String title; // title coming from previous activity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_result);

        // Initialize our view
        listOfCompanies = findViewById(R.id.list_of_companies);

        // Get the List of Companies
        companies = (ArrayList<Company>) getIntent().getExtras().getSerializable("listOfCompanies");

        // Get the title
        title = getIntent().getStringExtra("title");

        // set the title
        TextView companyTitle = findViewById(R.id.company_title);
        companyTitle.setText(title);

        // Create an Adapter of type adapterCompanyResult
        adapterCompanyResult = new AdapterCompanyResult(getApplicationContext(), companies);

        // set the adapter
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        listOfCompanies.setLayoutManager(layoutManager);
        listOfCompanies.setAdapter(adapterCompanyResult);
    }
}
