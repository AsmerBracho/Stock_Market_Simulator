package com.intelligence_1.stockmarketsimulator.model.companies;

import java.util.Comparator;

public class CompanyComparator implements Comparator<Company> {

    @Override
    public int compare(Company company1, Company company2) {
        return company1.getSharePrice().compareTo(company2.getSharePrice());
    }
}
