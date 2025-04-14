package lk.ijse.backend.dto;

import org.springframework.stereotype.Component;


public class ItemDTO {
    private int itemCode;
    private String itemName;
    private String description;
    private int quantity;
    private double price;
    private String location;
    private String sourceImage;
    private int categoryId;
    private String userEmail;

    public ItemDTO() {
    }

    public ItemDTO(int itemCode, String itemName, String description, int quantity, double price, String location, String sourceUrl, int categoryId, String userEmail) {
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        this.location = location;
        this.sourceImage = sourceUrl;
        this.categoryId = categoryId;
        this.userEmail = userEmail;
    }

    public int getItemCode() {
        return itemCode;
    }

    public void setItemCode(int itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSourceImage() {
        return sourceImage;
    }

    public void setSourceImage(String sourceImage) {
        this.sourceImage = sourceImage;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    @Override
    public String toString() {
        return "ItemDTO{" +
                "itemCode='" + itemCode + '\'' +
                ", itemName='" + itemName + '\'' +
                ", description='" + description + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", location='" + location + '\'' +
                ", sourceUrl='" + sourceImage + '\'' +
                ", categoryId='" + categoryId + '\'' +
                ", userId='" + userEmail + '\'' +
                '}';
    }
}
