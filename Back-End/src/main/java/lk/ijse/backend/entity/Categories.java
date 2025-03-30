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

    public Categories() {
    }

    public Categories(String categoryId, String name, List<Item> items) {
        this.categoryId = categoryId;
        this.name = name;
        this.items = items;
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

    @Override
    public String toString() {
        return "Categories{" +
                "categoryId='" + categoryId + '\'' +
                ", name='" + name + '\'' +
                ", items=" + items +
                '}';
    }
}
