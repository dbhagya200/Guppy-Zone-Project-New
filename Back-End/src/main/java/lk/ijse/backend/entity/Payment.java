package lk.ijse.backend.entity;

import jakarta.persistence.*;

@Entity
public class Payment {
    @Id
    private String paymentId;
    private String userId;
    private double amount;
    private String paymentDate;
    private String paymentMethod;
    private String paymentStatus;
    private String itemId;
}
