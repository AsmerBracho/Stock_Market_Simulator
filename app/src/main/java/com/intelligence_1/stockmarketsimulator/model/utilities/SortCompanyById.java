package com.intelligence_1.stockmarketsimulator.model.utilities;

import com.intelligence_1.stockmarketsimulator.model.companies.Company;

import java.util.Comparator;

public class SortCompanyById implements Comparator<Company>
{
    // Used for sorting in ascending order of
    // roll number

    @Override
    public int compare(Company c1, Company c2) {
        return  c1.getCompanyID()- c2.getCompanyID();
    }
}
