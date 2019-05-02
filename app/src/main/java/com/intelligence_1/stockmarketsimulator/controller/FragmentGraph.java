package com.intelligence_1.stockmarketsimulator.controller;

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
import com.intelligence_1.stockmarketsimulator.model.companies.Company;
import com.intelligence_1.stockmarketsimulator.model.investors.Investor;

import java.util.ArrayList;
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
        barChart(view);
        pieChart(view);

        return view;
    }



    // BarGraph
    public void barChart(View view) {
        BarChart barChart =  view.findViewById(R.id.graph);

        //// create BarEntry for incomes and expenses
        ArrayList<BarEntry> incomes = new ArrayList<>();
        ArrayList<BarEntry> expenses = new ArrayList<>();

        // create BarEntry for Bar Group 1
        incomes.add(new BarEntry(8f, 0));
        incomes.add(new BarEntry(2f, 1));
        incomes.add(new BarEntry(5f, 2));
        incomes.add(new BarEntry(20f, 3));
        incomes.add(new BarEntry(15f, 4));
        incomes.add(new BarEntry(19f, 5));

        // create BarEntry for Bar Group 2
        ArrayList<BarEntry> bargroup2 = new ArrayList<>();
        bargroup2.add(new BarEntry(6f, 0));
        bargroup2.add(new BarEntry(10f, 1));
        bargroup2.add(new BarEntry(5f, 2));
        bargroup2.add(new BarEntry(25f, 3));
        bargroup2.add(new BarEntry(4f, 4));
        bargroup2.add(new BarEntry(17f, 5));

        // creating dataset for Bar Group1
        BarDataSet barDataSet1 = new BarDataSet(incomes, "Bar Group 1");
        barDataSet1.setColor(Color.parseColor("#64f169"));

        BarDataSet barDataSet2 = new BarDataSet(bargroup2, "Bar Group 2");
        barDataSet2.setColor(Color.parseColor("#e45558"));

        // Labels
        ArrayList<String> labels = new ArrayList<String>();
        labels.add("1");
        labels.add("2");
        labels.add("3");
        labels.add("4");
        labels.add("5");
        labels.add("6");
        labels.add("7");
        labels.add("8");
        labels.add("9");
        labels.add("10");
        labels.add("11");
        labels.add("12");


        //combined Data Set
        ArrayList<BarDataSet> dataSets = new ArrayList<>();  // combined all dataset into an arraylist
        dataSets.add(barDataSet1);
        dataSets.add(barDataSet2);

        BarData data = new BarData(labels, dataSets);
        barChart.setData(data);
        barChart.animateY(1000);

    }

    public void pieChart(View view) {
        PieChart pieChart = (PieChart) view.findViewById(R.id.piechart);
        pieChart.setUsePercentValues(true);


        // IMPORTANT: In a PieChart, no values (Entry) should have the same
        // xIndex (even if from different DataSets), since no values can be
        // drawn above each other.
        ArrayList<Entry> yvalues = new ArrayList<Entry>();
        yvalues.add(new Entry(8f, 0));
        yvalues.add(new Entry(15f, 1));
        yvalues.add(new Entry(12f, 2));
        yvalues.add(new Entry(25f, 3));
        yvalues.add(new Entry(23f, 4));
        yvalues.add(new Entry(17f, 5));

        PieDataSet dataSet = new PieDataSet(yvalues, "Election Results");

        ArrayList<String> xVals = new ArrayList<String>();

        xVals.add("January");
        xVals.add("February");
        xVals.add("March");
        xVals.add("April");
        xVals.add("May");
        xVals.add("June");

        PieData data = new PieData(xVals, dataSet);

        data.setValueFormatter(new PercentFormatter());

        // setData
        pieChart.setData(data);
        pieChart.animateX(1000);
    }
}
