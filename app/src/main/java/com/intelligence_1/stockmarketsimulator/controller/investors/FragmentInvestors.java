/**
 * Stock Market Project
 * @authors Asmer Bracho (2016328),
 *          Gabriel Oliveira (2016310),
 *          Miguelantonio Guerra (2016324)
 */
package com.intelligence_1.stockmarketsimulator.controller.investors;

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
import com.intelligence_1.stockmarketsimulator.model.investors.Investor;
import java.util.List;

public class FragmentInvestors extends Fragment {

    // Global Variables
    private RecyclerView listOfInvestorFull; // list of n investors
    private Context context; // context of application
    private List<Investor> investors; // list of investors
    private AdapterInvestorsFull adapterInvestors;

    // Default Empty Constructor
    public FragmentInvestors() { }

    /**
     * Main Constructor of FragmentInvestor
     * @param investors a list of investors
     */
    @SuppressLint("ValidFragment")
    public FragmentInvestors(List<Investor> investors) {
        this.investors = investors;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_investors, container, false);

        setHasOptionsMenu(true);
        
        context = this.getContext(); // set the context
        // List of investors views
        listOfInvestorFull = view.findViewById(R.id.list_of_investors_full);

        // Create the Adapter
        adapterInvestors = new AdapterInvestorsFull(context, investors);

        // set the adapter
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        listOfInvestorFull.setLayoutManager(layoutManager);
        listOfInvestorFull.setAdapter(adapterInvestors);

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
                adapterInvestors.getFilter().filter(newText);
                return false;
            }
        });

    }


}
