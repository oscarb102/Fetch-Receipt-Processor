package com.fetch.receipt_processor.entity;

import java.util.Date;
import java.util.List;

public class Receipt {
    final String retailer;
    final Date purchaseDate;
    final Date purchaseTime;
    final double total;
    final List<Item> items;

    public Receipt(String retailer, Date purchaseDate, Date purchaseTime, List<Item> items) {
        this.retailer = retailer;
        this.purchaseDate = purchaseDate;
        this.purchaseTime = purchaseTime;
        this.items = items;
        this.total = setTotal(items);
    }

    private double setTotal(List<Item> items) {
        double res = 0;

        for (Item i: items) {
            res += i.price;
        }

        return res;
    }

    public String getRetailer() {
        return retailer;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public Date getPurchaseTime() {
        return purchaseTime;
    }

    public double getTotal() {
        return total;
    }

    public List<Item> getItems() {
        return items;
    }
}
