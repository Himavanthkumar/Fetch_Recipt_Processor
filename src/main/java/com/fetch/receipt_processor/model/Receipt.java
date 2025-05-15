package com.fetch.receipt_processor.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import java.util.List;

public class Receipt {
    @NotBlank(message = "retailer is required")
    @Pattern(regexp = "^[\\w\\s\\-&]+$", message = "Invalid retailer format")
    private String retailer;

    @NotBlank(message = "purchaseDate is required")
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Invalid purchaseDate format")
    private String purchaseDate;

    @NotBlank(message = "purchaseTime is required")
    @Pattern(regexp = "^\\d{2}:\\d{2}$", message = "Invalid purchaseTime format")
    private String purchaseTime;

    @NotEmpty(message = "At least one item is required")
    private List<Item> items;

    @NotBlank(message = "total is required")
    @Pattern(regexp = "^\\d+\\.\\d{2}$", message = "Invalid total format")
    private String total;

    // Getters and setters
    public String getRetailer() {
        return retailer;
    }

    public void setRetailer(String retailer) {
        this.retailer = retailer;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getPurchaseTime() {
        return purchaseTime;
    }

    public void setPurchaseTime(String purchaseTime) {
        this.purchaseTime = purchaseTime;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}