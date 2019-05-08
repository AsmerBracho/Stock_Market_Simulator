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
import android.view.View;
import android.view.ViewGroup;
import com.intelligence_1.stockmarketsimulator.R;
import com.intelligence_1.stockmarketsimulator.controller.investors.AdapterInvestorsFull;
import com.intelligence_1.stockmarketsimulator.model.investors.Investor;

import java.util.List;

public class FragmentInvestors extends Fragment {

    private RecyclerView listOfInvestorFull; // list of n investors
    private Context context; // context of application
    private List<Investor> investors; // list of investors

    // Default Empty Constructor
    public FragmentInvestors() {
    }

    @SuppressLint("ValidFragment")
    public FragmentInvestors(List<Investor> investors) {
        this.investors = investors;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_investors, container, false);

        context = this.getContext(); // set the context

        listOfInvestorFull = view.findViewById(R.id.list_of_investors_full);

        // Create the Adapter
        AdapterInvestorsFull adapterInvestors = new AdapterInvestorsFull(context, investors);

        // set the adapter
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        listOfInvestorFull.setLayoutManager(layoutManager);
        listOfInvestorFull.setAdapter(adapterInvestors);

        return view;

    }

}
