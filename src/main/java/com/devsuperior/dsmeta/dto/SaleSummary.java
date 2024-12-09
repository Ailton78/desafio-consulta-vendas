package com.devsuperior.dsmeta.dto;

public class SaleSummary {
    private String sellerName;
    private Double total;

    public SaleSummary(){}

    public SaleSummary(String sellerName, Double total) {
        this.sellerName = sellerName;
        this.total = total;
    }

    public String getSellerName() {
        return sellerName;
    }

    public Double getTotal() {
        return total;
    }
}
