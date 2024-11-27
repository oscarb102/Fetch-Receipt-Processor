package com.fetch.receipt_processor.utils;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.fetch.receipt_processor.entity.Item;
import com.fetch.receipt_processor.entity.Receipt;

public class PointsUtils {
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

    private static int alphanumericLettersInRetailName(String retailer) {
        int res = 0;

        for (char c: retailer.toCharArray()) {
            if (Character.isAlphabetic(c) || Character.isDigit(c)) {
                res++;
            }
        }

        return res;
    }

    private static int roundDollar(String total) {
        int n = total.length();
        return (total.charAt(n - 1) == '0' && total.charAt(n - 2) == '0') ? 50 : 0;
    }

    private static int totalIsMultiple(String total, double denominator) {
        double amount = Double.parseDouble(total);
        return (amount % denominator == 0) ? 25 : 0;
    }

    private static int everyTwoItems(int size) {
        return (size / 2) * 5;
    }

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

    private static int purchaseDayIsOdd(String date) {
        LocalDate parsedDate = LocalDate.parse(date);
        int day = parsedDate.getDayOfMonth();

        return (day % 2 != 0) ? 6 : 0;
    }

    private static int timeOfPurchase(String time) {
        LocalTime twoPm = LocalTime.of(14, 0, 0);
        LocalTime fourPm = LocalTime.of(16, 0,  0);
        LocalTime parsedTime = LocalTime.parse(time);
        boolean inTimeRange = (parsedTime.isAfter(twoPm) && parsedTime.isBefore(fourPm));

        return (inTimeRange) ? 10 : 0;
    }
}
