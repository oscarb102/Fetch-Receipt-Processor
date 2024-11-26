package com.fetch.receipt_processor.entity;

public class Item {
    final String shortDescription;
    final double price;

    public Item(String shortDescription, double price) {
        this.shortDescription = shortDescription;
        this.price = price;
    }

    public String getShortDescription() {
        return this.shortDescription;
    }

    public double getPrice() {
        return price;
    }
}
