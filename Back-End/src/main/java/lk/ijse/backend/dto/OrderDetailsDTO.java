package lk.ijse.backend.dto;

public class OrderDetailsDTO {

    private String id;
    private String orderId;
    private String itemId;
    private int quantity;
    private double Price;

    public OrderDetailsDTO() {
    }

    public OrderDetailsDTO(String id, String orderId, String itemId, int quantity, double price) {
        this.id = id;
        this.orderId = orderId;
        this.itemId = itemId;
        this.quantity = quantity;
        Price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    @Override
    public String toString() {
        return "OrderDetailsDTO{" +
                "id='" + id + '\'' +
                ", orderId='" + orderId + '\'' +
                ", itemId='" + itemId + '\'' +
                ", quantity=" + quantity +
                ", Price=" + Price +
                '}';
    }
}
