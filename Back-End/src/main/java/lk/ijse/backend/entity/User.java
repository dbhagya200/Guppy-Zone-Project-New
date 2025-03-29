package lk.ijse.backend.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uid;
    @Column(unique = true)
    private String username;
    @Column(unique = true)
    private String email;
    private String password;
    private String role;
    private String image;
    private String address;
    private String contact;
    private String firstName;
    private String lastName;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Item> items;
    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Categories> categories;
    @OneToMany(mappedBy = "username", cascade = CascadeType.ALL)
    private List<ReviewsRatings> reviewsRatings;

    public User() {
    }

    public User(UUID uid, String username, String email, String password, String role, String image, String address, String contact, String firstName, String lastName, List<Item> items, List<Categories> categories, List<ReviewsRatings> reviewsRatings) {
        this.uid = uid;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
        this.image = image;
        this.address = address;
        this.contact = contact;
        this.firstName = firstName;
        this.lastName = lastName;
        this.items = items;
        this.categories = categories;
        this.reviewsRatings = reviewsRatings;
    }

    public UUID getUid() {
        return uid;
    }

    public void setUid(UUID uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public List<Categories> getCategories() {
        return categories;
    }

    public void setCategories(List<Categories> categories) {
        this.categories = categories;
    }

    public List<ReviewsRatings> getReviewsRatings() {
        return reviewsRatings;
    }

    public void setReviewsRatings(List<ReviewsRatings> reviewsRatings) {
        this.reviewsRatings = reviewsRatings;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", image='" + image + '\'' +
                ", address='" + address + '\'' +
                ", contact='" + contact + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", items=" + items +
                ", categories=" + categories +
                ", reviewsRatings=" + reviewsRatings +
                '}';
    }
}
