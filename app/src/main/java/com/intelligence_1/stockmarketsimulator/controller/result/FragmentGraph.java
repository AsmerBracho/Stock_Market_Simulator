package com.intelligence_1.stockmarketsimulator.controller.result;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.intelligence_1.stockmarketsimulator.R;
import com.intelligence_1.stockmarketsimulator.controller.StockMarketDAO;
import com.intelligence_1.stockmarketsimulator.model.companies.Company;
import com.intelligence_1.stockmarketsimulator.model.investors.Investor;
import com.intelligence_1.stockmarketsimulator.model.utilities.SortCompanyById;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class FragmentGraph extends Fragment {

    private Context context;
    private List<Investor> investors;
    private List<Company> companies;

    // Default Empty Constructor
    public FragmentGraph () { }

    @SuppressLint("ValidFragment")
    public FragmentGraph(List<Company> companies, List<Investor> investors) {
        this.companies = companies;
        this.investors = investors;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_graph, container, false);

        this.context = this.getContext();

        // init graphs
        barChartCompanies(view);
        barChartInvestor(view);

        return view;

    }

    /**
     * Method BarChart
     * Method that initialize the view and handle the necessary data to put in
     * the graph (bar chart)
     *
     * @param view
     */
    public void barChartCompanies(View view) {
        BarChart barChart = view.findViewById(R.id.graph);

        //// create BarEntry for incomes and expenses
        ArrayList<BarEntry> capital = new ArrayList<>();
        ArrayList<String> labels = new ArrayList<String>();

        Collections.sort(companies, new SortCompanyById());

        int i =0;
        for (Company c : companies) {
            labels.add(String.valueOf(i));
                float totalCapital = (float) (c.getCompanyNumberOfShares()*c.getSharePrice());
                capital.add(new BarEntry((float) totalCapital, i));
            i++;
        }

        // creating dataset for capital
        BarDataSet barIncomes = new BarDataSet(capital, "Companies Capital");
        barIncomes.setColor(Color.parseColor("#2ed5e4"));

        BarData data = new BarData(labels, barIncomes);
        barChart.setDescription("Companies");
        barChart.setData(data);
        barChart.animateY(1000);
    }

    /**
     * Method BarChart
     * Method that initialize the view and handle the necessary data to put in
     * the graph (bar chart)
     *
     * @param view
     */
    public void barChartInvestor(View view) {
        BarChart barChart = view.findViewById(R.id.barChart2);

        //// create BarEntry for incomes and expenses
        ArrayList<BarEntry> shares = new ArrayList<>();
        ArrayList<String> labels = new ArrayList<String>();

        int i =0;
        for (Investor inv : investors) {
            labels.add(String.valueOf(i));
            float sharesInv = (float) (inv.getInvestorNumberOfBoughtShares());
            shares.add(new BarEntry((float) sharesInv, i));
            i++;
        }

        // creating data set for capital
        BarDataSet barIncomes = new BarDataSet(shares, "Investors Shares Bought");
        barIncomes.setColor(Color.parseColor("#2eb945"));

        BarData data = new BarData(labels, barIncomes);
        barChart.setDescription("Investor");
        barChart.setData(data);
        barChart.animateY(1000);
    }

}
