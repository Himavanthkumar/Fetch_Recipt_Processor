package com.fetch.receipt_processor.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class Item {
    @NotBlank(message = "shortDescription is required")
    @Pattern(regexp = "^[\\w\\s\\-]+$", message = "Invalid shortDescription format")
    private String shortDescription;

    @NotBlank(message = "price is required")
    @Pattern(regexp = "^\\d+\\.\\d{2}$", message = "Invalid price format")
    private double price;

    // Getters and setters
    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}