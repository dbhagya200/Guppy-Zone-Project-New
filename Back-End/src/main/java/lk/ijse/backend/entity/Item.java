package lk.ijse.backend.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
public class Item implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int itemCode;
    private String itemName;
    private String description;
    private int quantity;
    private double price;
    private String location;
    private String sourceImage;
    @ManyToOne
    private Categories category;
    @ManyToOne
    private User user;
    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private List<ReviewsRatings> reviewsRatings;
    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderDetails> orderDetails;

    public Item() {
    }

    public Item(int itemCode, String itemName, String description, int quantity, double price, String location, String sourceUrl, Categories category, User user, List<ReviewsRatings> reviewsRatings, List<OrderDetails> orderDetails) {
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        this.location = location;
        this.sourceImage = sourceUrl;
        this.category = category;
        this.user = user;
        this.reviewsRatings = reviewsRatings;
        this.orderDetails = orderDetails;
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

    public Categories getCategory() {
        return category;
    }

    public void setCategory(Categories category) {
        this.category = category;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<ReviewsRatings> getReviewsRatings() {
        return reviewsRatings;
    }

    public void setReviewsRatings(List<ReviewsRatings> reviewsRatings) {
        this.reviewsRatings = reviewsRatings;
    }

    public List<OrderDetails> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetails> orderDetails) {
        this.orderDetails = orderDetails;
    }

    //    @Override
//    public String toString() {
//        return "Item{" +
//                "itemCode='" + itemCode + '\'' +
//                ", itemName='" + itemName + '\'' +
//                ", description='" + description + '\'' +
//                ", quantity=" + quantity +
//                ", price=" + price +
//                ", location='" + location + '\'' +
//                ", sourceUrl='" + sourceUrl + '\'' +
//                ", category=" + category +
//                ", user=" + user +
//                ", reviewsRatings=" + reviewsRatings +
//                ", orderDetails=" + orderDetails +
//                '}';
//    }
}
