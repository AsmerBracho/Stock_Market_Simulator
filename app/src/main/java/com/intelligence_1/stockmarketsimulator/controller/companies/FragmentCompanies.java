/**
 * Stock Market Project
 * @authors Asmer Bracho (2016328),
 *          Gabriel Oliveira (2016310),
 *          Miguelantonio Guerra (2016324)
 */
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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;

import com.intelligence_1.stockmarketsimulator.R;
import com.intelligence_1.stockmarketsimulator.model.companies.Company;
import java.util.List;

/**
 * Public class Fragment Companies
 * This is the container from where we call our list of records
 * and results for Companies
 */
public class FragmentCompanies extends Fragment {

    // Global Variables
    private RecyclerView listOfCompaniesFull; // list of n comapanies
    private Context context; // context of application
    private List<Company> companies; // list of companies
    private AdapterCompaniesFull adapterCompanies;

    // Default Empty Constructor
    public FragmentCompanies() {
    }

    /**
     * Constructor FragmentCompanies
     * @param companies a lis of companies
     */
    @SuppressLint("ValidFragment")
    public FragmentCompanies(List<Company> companies) {
        this.companies = companies;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_companies, container, false);

        setHasOptionsMenu(true);

        context = this.getContext(); // set the context

        // Get the list of companies view
        listOfCompaniesFull = view.findViewById(R.id.list_of_companies_full);

        // Create the Adapter
        adapterCompanies = new AdapterCompaniesFull(context, companies);

        // set the adapter
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        listOfCompaniesFull.setLayoutManager(layoutManager);
        listOfCompaniesFull.setAdapter(adapterCompanies);

        return view;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.filter_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        android.support.v7.widget.SearchView searchView = (android.support.v7.widget.SearchView) searchItem.getActionView();

        // Change the IME to DONE rather that the default search
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        searchView.setOnQueryTextListener(new android.support.v7.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapterCompanies.getFilter().filter(newText);
                return false;
            }
        });

    }


}
