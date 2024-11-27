package com.fetch.receipt_processor.controller.receipt;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fetch.receipt_processor.service.ReceiptManager;
import com.fetch.receipt_processor.utils.IdGeneratorUtils;
import com.fetch.receipt_processor.utils.PointsUtils;

import java.util.Map;
import com.fetch.receipt_processor.entity.Receipt;

@RestController
public class ReceiptController {

    @GetMapping
    @RequestMapping("/receipts")
    public Map<String, Receipt> getAllReceipts() {
        ReceiptManager receiptManager = ReceiptManager.getInstance();

        return receiptManager.getReceipts();
    }

    @PostMapping
    @RequestMapping("receipts/process")
    public ResponseEntity<Map<String, String>> addReceipt(@RequestBody Receipt receipt) {
        ReceiptManager receiptManager = ReceiptManager.getInstance();

        String id = IdGeneratorUtils.createId();
        receiptManager.addReceipt(id, receipt);

        Map<String, String> response = Map.of("id", id);
        return new ResponseEntity<Map<String, String>>(response, HttpStatus.CREATED);
    }

    @GetMapping
    @RequestMapping("receipts/{id}/points")
    public ResponseEntity<Map<String, String>> getPointsById(@PathVariable("id") String receiptId) {
        ReceiptManager receiptManager = ReceiptManager.getInstance();

        if (!receiptManager.getReceipts().containsKey(receiptId)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        int points = PointsUtils.getPoints(receiptManager.getReceipts().get(receiptId));

        Map<String, String> response = Map.of("points", String.valueOf(points));
        return new ResponseEntity<Map<String, String>>(response, HttpStatus.OK);
    }
}