package com.fetch.receipt_processor.utils;

import com.fetch.receipt_processor.entity.Receipt;

public class PointsUtils {
    public static int getPoints(Receipt receipt) {
        int res = 0;

        res += alphanumericLettersInRetailName(receipt.getRetailer());
        res += roundDollar(receipt.getTotal());
        res += totalIsMultiple(receipt.getTotal(), .25);
        res += everyTwoItems(receipt.getItems().size());

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
}
