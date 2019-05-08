package com.intelligence_1.stockmarketsimulator.controller.companies;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.intelligence_1.stockmarketsimulator.R;
import com.intelligence_1.stockmarketsimulator.controller.companies.AdapterCompaniesFull;
import com.intelligence_1.stockmarketsimulator.model.companies.Company;

import java.util.List;

public class FragmentCompanies extends Fragment {

    private RecyclerView listOfCompaniesFull; // list of n comapanies
    private Context context; // context of application
    private List<Company> companies; // list of companies

    // Default Empty Constructor
    public FragmentCompanies() {
    }

    @SuppressLint("ValidFragment")
    public FragmentCompanies(List<Company> companies) {
        this.companies = companies;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_companies, container, false);

        context = this.getContext(); // set the context

        listOfCompaniesFull = view.findViewById(R.id.list_of_companies_full);

        // Create the Adapter
        AdapterCompaniesFull adapterCompanies = new AdapterCompaniesFull(context, companies);

        // set the adapter
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        listOfCompaniesFull.setLayoutManager(layoutManager);
        listOfCompaniesFull.setAdapter(adapterCompanies);

        return view;

    }

}
