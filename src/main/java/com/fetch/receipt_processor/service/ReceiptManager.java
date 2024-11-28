package com.fetch.receipt_processor.service;

import java.util.Map;
import java.util.HashMap;
import com.fetch.receipt_processor.entity.Receipt;

/**
 * Represents a ReceiptManager instance
 * 
 * <p>
 * This class uses the Singleton design pattern since 
 * there should only exist one map of Receipt entries.
 * </p>
 */
public class ReceiptManager {
    private static ReceiptManager instance;
    private Map<String, Receipt> receipts;

    private ReceiptManager() {
        receipts = new HashMap<>();
    }

    public static ReceiptManager getInstance() {
        if (instance == null) {
            instance = new ReceiptManager();
        }
        return instance;
    }

    public void addReceipt(String id, Receipt receipt) {
        receipts.put(id, receipt);
    }

    public Map<String, Receipt> getReceipts() {
        return receipts;
    }
}
