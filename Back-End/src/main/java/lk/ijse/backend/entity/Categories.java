package lk.ijse.backend.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Categories {
    @Id
    private String categoryId;
    private String name;
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Item> items;
    @ManyToOne
    @JoinColumn(name = "seller_id") // Match User's ID field
    private User seller;

    public Categories() {
    }

    public Categories(String categoryId, String name, List<Item> items, User seller) {
        this.categoryId = categoryId;
        this.name = name;
        this.items = items;
        this.seller = seller;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    @Override
    public String toString() {
        return "Categories{" +
                "categoryId='" + categoryId + '\'' +
                ", name='" + name + '\'' +
                ", items=" + items +
                ", seller=" + seller +
                '}';
    }
}
