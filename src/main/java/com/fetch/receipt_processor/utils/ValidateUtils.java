package com.fetch.receipt_processor.utils;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.List;
import java.util.regex.Pattern;

import com.fetch.receipt_processor.entity.Item;
import com.fetch.receipt_processor.entity.Receipt;

/**
 * Represents a utility class to validate Receipts.
 */
public class ValidateUtils {
    
    /**
     * Validate if proved receipt payload is valid.
     * 
     * @param receipt The receipt object provided by the payload
     * @return True if receipt is valid. Otherwise, false.
     */
    public static boolean validateReceipt(Receipt receipt) {
        if (receipt == null) {
            return false;
        }

        if (!validRetailer(receipt.getRetailer()) ||
            !validPurchaseDate(receipt.getPurchaseDate()) ||
            !validPurchaseTime(receipt.getPurchaseTime()) ||
            !validateItems(receipt.getItems()) ||
            !validateTotal(receipt.getTotal())
        ) {
            return false;
        }

        return true;
    }

    /**
     * Validate the Retailer string.
     * 
     * @param retailer The retailer string
     * @return True if retailer is valid. Otherwise, false.
     */
    private static boolean validRetailer(String retailer) {
        if (retailer == null) {
            return false;
        }

        return Pattern.matches("^[\\w\\s\\-\\&]+$", retailer);
    }

    /**
     * Validate the Purchase Date string.
     * 
     * @param date The date string
     * @return True if the date string is valid. Otherwise, false.
     */
    private static boolean validPurchaseDate(String date) {
        if (date == null) {
            return false;
        }

        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("uuuu-MM-dd")
        .withResolverStyle(ResolverStyle.STRICT);

        try {
            LocalDate.parse(date, dateFormat);
        } catch (DateTimeParseException dtpe) {
            return false;
        }

        return true;
    }

    /**
     * Validate the Purchase Time string.
     * 
     * @param time The time string
     * @return True if the time string is valid. Otherwise, false.
     */
    private static boolean validPurchaseTime(String time) {
        if (time == null) {
            return false;
        }

        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        try {
            LocalTime.parse(time, timeFormatter);
        } catch (DateTimeParseException dtpe) {
            return false;
        }

        return true;
    }

    /**
     * Validates the Items objects.
     * 
     * @param items The list of Items
     * @return True if all items are valid. Otherwise, false.
     */
    private static boolean validateItems(List<Item> items) {
        if (items == null || items.size() == 0) {
            return false;
        }

        for (Item item: items) {
            String itemDesc = item.getShortDescription();
            String itemPrice = item.getPrice();
            if (!validateItemShortDesc(itemDesc) || !validateItemPrice(itemPrice)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Validates the Short Description.
     * 
     * @param shortDesc Item Short description
     * @return True if the Item short description is valid. Otherwise, false.
     */
    private static boolean validateItemShortDesc(String shortDesc) {
        if (shortDesc == null) {
            return false;
        }

        return Pattern.matches("^[\\w\\s\\-]+$", shortDesc);
    }

    /**
     * Validates the Item Price.
     * 
     * @param price Item Price
     * @return True if the Item price is valid. Otherwise, false.
     */
    private static boolean validateItemPrice(String price) {
        if (price == null) {
            return false;
        }

        return Pattern.matches("^\\d+\\.\\d{2}$", price);
    }

    /**
     * Validates the Total string
     * 
     * @param total The total string
     * @return True if the total is valid. Otherwise, false.
     */
    private static boolean validateTotal(String total) {
        if (total == null) {
            return false;
        }

        return Pattern.matches("^\\d+\\.\\d{2}$", total);
    }
}
