package lk.ijse.backend.dto;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lk.ijse.backend.entity.Orders;
import lk.ijse.backend.entity.User;

public class PaymentDTO {
    private String paymentId;
    private UserDTO userId;
    private OrderDTO orders;
    private double amount;
    private String paymentDate;
    private String paymentMethod;
    private String paymentStatus;

    public PaymentDTO() {
    }

    public PaymentDTO(String paymentId, UserDTO userId, OrderDTO orders, double amount, String paymentDate, String paymentMethod, String paymentStatus) {
        this.paymentId = paymentId;
        this.userId = userId;
        this.orders = orders;
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.paymentMethod = paymentMethod;
        this.paymentStatus = paymentStatus;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public UserDTO getUserId() {
        return userId;
    }

    public void setUserId(UserDTO userId) {
        this.userId = userId;
    }

    public OrderDTO getOrders() {
        return orders;
    }

    public void setOrders(OrderDTO orders) {
        this.orders = orders;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    @Override
    public String toString() {
        return "PaymentDTO{" +
                "paymentId='" + paymentId + '\'' +
                ", userId=" + userId +
                ", orders=" + orders +
                ", amount=" + amount +
                ", paymentDate='" + paymentDate + '\'' +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", paymentStatus='" + paymentStatus + '\'' +
                '}';
    }
}
