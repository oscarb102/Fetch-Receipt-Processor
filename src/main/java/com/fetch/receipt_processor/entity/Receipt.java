package com.fetch.receipt_processor.entity;

import java.util.List;

/**
 * Represents a Receipt object
 * 
 * @author Oscar Bartolo
 */
public class Receipt {
    final String retailer;
    final String purchaseDate;
    final String purchaseTime;
    final String total;
    final List<Item> items;

    public Receipt(String retailer, String purchaseDate, String purchaseTime, String total, List<Item> items) {
        this.retailer = retailer;
        this.purchaseDate = purchaseDate;
        this.purchaseTime = purchaseTime;
        this.items = items;
        this.total = total;
    }

    public String getRetailer() {
        return retailer;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public String getPurchaseTime() {
        return purchaseTime;
    }

    public String getTotal() {
        return total;
    }

    public List<Item> getItems() {
        return items;
    }
}
