package lk.ijse.backend.entity;

import jakarta.persistence.*;

@Entity
public class Payment {
    @Id
    private String paymentId;
    @ManyToOne
    private User userId;
    @OneToOne
    private Orders orders;
    private double amount;
    private String paymentDate;
    private String paymentMethod;
    private String paymentStatus;

    public Payment() {
    }

    public Payment(String paymentId, User userId, Orders orders, double amount, String paymentDate, String paymentMethod, String paymentStatus) {
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

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
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
        return "Payment{" +
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
