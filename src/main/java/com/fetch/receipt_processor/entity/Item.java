package com.fetch.receipt_processor.entity;

/**
 * Represents an Item object that is part of a Receipt
 * 
 * @author Oscar Bartolo
 */
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
