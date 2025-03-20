package lk.ijse.backend.entity;

import jakarta.persistence.*;

@Entity
public class OrderDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int quantity;
    private double price;
    @ManyToOne
    private Orders orders;
    @ManyToOne
    private Item item;

    public OrderDetails() {
    }

    public OrderDetails(int id, int quantity, double price, Orders orders, Item item) {
        this.id = id;
        this.quantity = quantity;
        this.price = price;
        this.orders = orders;
        this.item = item;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return "OrderDetails{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", price=" + price +
                ", orders=" + orders +
                ", item=" + item +
                '}';
    }
}
