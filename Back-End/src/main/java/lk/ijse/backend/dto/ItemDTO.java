package lk.ijse.backend.dto;

import org.springframework.stereotype.Component;

@Component
public class ItemDTO {
    private String itemCode;
    private String itemName;
    private String description;
    private int quantity;
    private double price;
    private String location;
    private String sourceUrl;
    private String categoryId;
    private String userEmail;

    public ItemDTO() {
    }

    public ItemDTO(String itemCode, String itemName, String description, int quantity, double price, String location, String sourceUrl, String categoryId, String userEmail) {
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        this.location = location;
        this.sourceUrl = sourceUrl;
        this.categoryId = categoryId;
        this.userEmail = userEmail;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
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

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
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
                ", sourceUrl='" + sourceUrl + '\'' +
                ", categoryId='" + categoryId + '\'' +
                ", userId='" + userEmail + '\'' +
                '}';
    }
}
