package lk.ijse.backend.dto;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;

@Component
public class OrderDTO {
    private int orderId;
    private String userId;
    private String itemName;
    private double totalAmount;
    private LocalDate date;
    private LocalTime time;
    private String status;

    public OrderDTO() {
    }

    public OrderDTO(int orderId, String userId, String itemName, double totalAmount, LocalDate date, LocalTime time, String status) {
        this.orderId = orderId;
        this.userId = userId;
        this.itemName = itemName;
        this.totalAmount = totalAmount;
        this.date = date;
        this.time = time;
        this.status = status;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "orderId=" + orderId +
                ", userId='" + userId + '\'' +
                ", itemName='" + itemName + '\'' +
                ", totalAmount=" + totalAmount +
                ", date=" + date +
                ", time=" + time +
                ", status='" + status + '\'' +
                '}';
    }
}
