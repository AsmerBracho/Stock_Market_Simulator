package com.intelligence_1.stockmarketsimulator.controller.investors;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.intelligence_1.stockmarketsimulator.R;
import com.intelligence_1.stockmarketsimulator.controller.investors.AdapterInvestorResult;
import com.intelligence_1.stockmarketsimulator.model.companies.Company;
import com.intelligence_1.stockmarketsimulator.model.investors.Investor;

import java.util.ArrayList;
import java.util.List;

public class InvestorResult extends AppCompatActivity {

    private RecyclerView listOfInvestors; // Views for the list of investor
    private List<Investor> investors;
    private List<Company> companies;
    private AdapterInvestorResult adapterInvestorResult;
    private String title; // title coming from previous activity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_investor_result);

        // Initialize our view
        listOfInvestors = findViewById(R.id.list_of_investors);

        // Get the List of investors and Companies
        investors = (ArrayList<Investor>) getIntent().getExtras().getSerializable("listOfInvestors");
        companies = (ArrayList<Company>) getIntent().getExtras().getSerializable("listOfCompanies");

        // Get the title
        title = getIntent().getStringExtra("title");

        // set the title
        TextView investorTitle = findViewById(R.id.investor_title);
        investorTitle.setText(title);

        // Create an Adapter of type adapterInvestorResult
        adapterInvestorResult = new AdapterInvestorResult(getApplicationContext(), investors, companies);

        // set the adapter
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        listOfInvestors.setLayoutManager(layoutManager);
        listOfInvestors.setAdapter(adapterInvestorResult);
    }




}
