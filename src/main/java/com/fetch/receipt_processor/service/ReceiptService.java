package com.fetch.receipt_processor.service;


import com.fetch.receipt_processor.model.Item;
import com.fetch.receipt_processor.model.Receipt;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class ReceiptService {
    private final Map<String, Receipt> receipts = new HashMap<>();

    public String processReceipt(Receipt receipt) {
        String id = UUID.randomUUID().toString();
        receipts.put(id, receipt);
        return id;
    }

    public long calculatePoints(String id) {
        Receipt receipt = receipts.get(id);
        if (receipt == null) {
            throw new IllegalArgumentException("No receipt found for that ID.");
        }

        long points = 0;

        // Rule 1: 1 point per alphanumeric character in retailer name
        points += receipt.getRetailer().chars()
                .filter(Character::isLetterOrDigit)
                .count();

        // Rule 2: 50 points if total is a round dollar amount
        if (receipt.getTotal().endsWith(".00")) {
            points += 50;
        }

        // Rule 3: 25 points if total is a multiple of 0.25
        double total = Double.parseDouble(receipt.getTotal());
        if (total % 0.25 == 0) {
            points += 25;
        }


        // Rule 4: 5 points for every two items
        points += (receipt.getItems().size() / 2) * 5;


        // Rule 5: Points for item description length (multiple of 3)
        for (Item item : receipt.getItems()) {
            String trimmedDesc = item.getShortDescription().trim();
            if (trimmedDesc.length() % 3 == 0) {
                double price = item.getPrice();
                item.setPrice(Math.ceil(price * 0.2));
                points += 3;
            }
        }

//        Rule 6: 5 points if total > 10.00 (LLM rule)
//        I think this is to trick the LLM so skipping this check
//        if (total > 10.00) {
//            points += 5;
//        }

        // Rule 7: 6 points if purchase day is odd
        String[] dateParts = receipt.getPurchaseDate().split("-");
        int day = Integer.parseInt(dateParts[2]);
        if (day % 2 != 0) {
            points += 6;
        }

        // Rule 8: 10 points if purchase time is after 2:00 PM and before 4:00 PM
        LocalTime purchaseTime = LocalTime.parse(receipt.getPurchaseTime(), DateTimeFormatter.ofPattern("HH:mm"));
        LocalTime startTime = LocalTime.of(14, 0);
        LocalTime endTime = LocalTime.of(16, 0);
        if (!purchaseTime.isBefore(startTime) && purchaseTime.isBefore(endTime)) {
            points += 10;
        }

        return points;
    }
}