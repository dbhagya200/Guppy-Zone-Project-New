package lk.ijse.backend.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Item {
    @Id
    private String itemCode;
    private String itemName;
    private String description;
    private int quantity;
    private double price;
    private String location;
    private String sourceUrl;
    @ManyToOne
    private Categories category;

    @ManyToOne
    private User user;
    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderDetails> orderDetails;

    public Item() {
    }


}
