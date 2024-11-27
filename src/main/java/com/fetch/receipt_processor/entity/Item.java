package com.fetch.receipt_processor.entity;

public class Item {
    final String shortDescription;
    final String price;

    public Item(String shortDescription, String price) {
        this.shortDescription = shortDescription;
        this.price = price;
    }

    public String getShortDescription() {
        return this.shortDescription;
    }

    public String getPrice() {
        return price;
    }
}
