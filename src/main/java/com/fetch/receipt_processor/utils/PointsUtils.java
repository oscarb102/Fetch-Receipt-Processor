package com.fetch.receipt_processor.utils;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.fetch.receipt_processor.entity.Item;
import com.fetch.receipt_processor.entity.Receipt;

/**
 * Represents a utility class to calculate Points.
 * 
 * <p>
 * Points are based on a certain set of rules.
 * </p>
 */
public class PointsUtils {

    /**
     * Calculates total points for the provided Receipt.
     * 
     * @param receipt The Receipt object
     * @return Total points for receipt
     */
    public static int getPoints(Receipt receipt) {
        int res = 0;

        res += alphanumericLettersInRetailName(receipt.getRetailer());
        res += roundDollar(receipt.getTotal());
        res += totalIsMultiple(receipt.getTotal(), .25);
        res += everyTwoItems(receipt.getItems().size());
        res += trimmedItemDescriptions(receipt.getItems());
        res += purchaseDayIsOdd(receipt.getPurchaseDate());
        res += timeOfPurchase(receipt.getPurchaseTime());

        return res;
    }

    /**
     * Calculates the points for retailer names.
     * 
     * <p>
     * The calculation is based on the number of
     * alphanumeric characters in the retailer
     * name.
     * </p>
     * 
     * @param retailer The retailer name
     * @return Count of alphanumeric characters
     */
    private static int alphanumericLettersInRetailName(String retailer) {
        int res = 0;

        for (char c: retailer.toCharArray()) {
            if (Character.isAlphabetic(c) || Character.isDigit(c)) {
                res++;
            }
        }

        return res;
    }

    /**
     * Calculates points based on the total.
     * 
     * <p>
     * The calculation is based on if the total
     * is a round dollar amount.
     * </p>
     * 
     * @param total The total price of all items
     * @return 50 points if total is a round number. Otherwise, 0 is returned
     */
    private static int roundDollar(String total) {
        int n = total.length();
        return (total.charAt(n - 1) == '0' && total.charAt(n - 2) == '0') ? 50 : 0;
    }

    /**
     * Calculates points if total is a multiple of denominator.
     * 
     * 
     * @param total The total price of all items
     * @param denominator The number to determine check total is a multiple of
     * @return 25 points if total is a multiple of denominator. Otherwise, 0 is returned
     */
    private static int totalIsMultiple(String total, double denominator) {
        double amount = Double.parseDouble(total);
        return (amount % denominator == 0) ? 25 : 0;
    }

    /**
     * Calculates points based on number of items
     * 
     * <p>
     * For every two items, 5 points are added
     * </p>
     * 
     * @param size The number of Items
     * @return 5 points for every two items
     */
    private static int everyTwoItems(int size) {
        return (size / 2) * 5;
    }

    /**
     * Calculates points based on item short descriptions
     * 
     * <p>
     * This calculation is based on a formula where, if the
     * item description is multiple of 3, multiply the item
     * price by 0.2 and round up to the nearest integer.
     * </p>
     * 
     * @param items The list of Items
     * @return Points based on the formula
     */
    private static int trimmedItemDescriptions(List<Item> items) {
        int res = 0;

        for (Item item: items) {
            String trimmedItemDesc = item.getShortDescription().trim();
            if (trimmedItemDesc.length() % 3 == 0) {
                double price = Double.parseDouble(item.getPrice());
                res += (int) Math.ceil(price * .2);
            }
        }

        return res;
    }

    /**
     * Calculates points if the day is odd.
     * 
     * @param date The Date string
     * @return 6 points if Day is odd. Otherwise, 0 is returned.
     */
    private static int purchaseDayIsOdd(String date) {
        LocalDate parsedDate = LocalDate.parse(date);
        int day = parsedDate.getDayOfMonth();

        return (day % 2 != 0) ? 6 : 0;
    }

    /**
     * Calculates points if purchase time is between 2:00 pm 
     * and 4:00 pm.
     * 
     * @param time The Time string
     * @return 10 points if time is between range. Otherwise, 0 is returned.
     */
    private static int timeOfPurchase(String time) {
        LocalTime twoPm = LocalTime.of(14, 0, 0);
        LocalTime fourPm = LocalTime.of(16, 0,  0);
        LocalTime parsedTime = LocalTime.parse(time);
        boolean inTimeRange = (parsedTime.isAfter(twoPm) && parsedTime.isBefore(fourPm));

        return (inTimeRange) ? 10 : 0;
    }
}
