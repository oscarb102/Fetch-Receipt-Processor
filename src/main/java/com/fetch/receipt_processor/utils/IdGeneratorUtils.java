package com.fetch.receipt_processor.utils;

import java.util.Random;

import com.fetch.receipt_processor.service.ReceiptManager;

public class IdGeneratorUtils {
    final static char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".toCharArray();
    final static int idLength = 36;
    
    public static String createId() {
        ReceiptManager receiptManager = ReceiptManager.getInstance();
        String generatedId = generateId();
        while (receiptManager.getReceipts().containsKey(generatedId)) {
            generatedId = generateId();
        }

        return generatedId;
    }

    private static String generateId() {
        StringBuilder res = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < idLength; i++) {
            res.append(chars[random.nextInt(chars.length)]);
        }

        res.replace(8, 8, "-");
        res.replace(13, 13, "-");
        res.replace(18, 18, "-");
        res.replace(23, 23, "-");

        return res.toString();
    }
}