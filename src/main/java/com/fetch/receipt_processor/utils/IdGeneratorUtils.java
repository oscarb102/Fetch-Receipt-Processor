package com.fetch.receipt_processor.utils;

import java.util.Random;

import com.fetch.receipt_processor.service.ReceiptManager;

/**
 * Represents a utility class to generate IDs.
 */
public class IdGeneratorUtils {
    final static char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".toCharArray();
    final static int idLength = 36;
    
    /**
     * Creates an ID entry within the ReceiptManager.receipts map.
     * 
     * <p>
     * In the odd case that the ID exists, createId() will continue
     * to generate IDs until a unique ID is created.
     * </p>
     * 
     * @return The generated ID string
     */
    public static String createId() {
        ReceiptManager receiptManager = ReceiptManager.getInstance();
        String generatedId = generateId();
        while (receiptManager.getReceipts().containsKey(generatedId)) {
            generatedId = generateId();
        }

        return generatedId;
    }

    /**
     * Generates an ID from Alphanumeric characters.
     * 
     * <p>
     * The generated ID will look like: 
     * nJTPWS5C-E5mV-bvHm-zLlE-c9nbGio0PSHSFsJt
     * <p>
     * 
     * @return The generated ID String
     */
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