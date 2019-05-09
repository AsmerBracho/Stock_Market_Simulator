package com.intelligence_1.stockmarketsimulator.controller.graphql;

public class SharesGraphQL {

    private int sharesTraded;
    private int companyId;
    private int investorId;

    public SharesGraphQL (int sharesTraded, int companyId, int investorId) {
        this.sharesTraded = sharesTraded;
        this.companyId = companyId;
        this.investorId = investorId;
    }

    public int getSharesTraded() {
        return sharesTraded;
    }

    public int getCompanyId() {
        return companyId;
    }

    public int getInvestorId() {
        return investorId;
    }
}
