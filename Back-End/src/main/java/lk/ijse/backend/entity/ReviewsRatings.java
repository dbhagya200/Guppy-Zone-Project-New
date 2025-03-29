package lk.ijse.backend.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
public class ReviewsRatings {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long reviewId;
    private String review;
    private int rating;
    private Timestamp date;
    @ManyToOne
    private User username;
    @ManyToOne
    private Item item;

    public ReviewsRatings() {
    }

    public ReviewsRatings(Long reviewId, String review, int rating, Timestamp date, User username, Item item) {
        this.reviewId = reviewId;
        this.review = review;
        this.rating = rating;
        this.date = date;
        this.username = username;
        this.item = item;
    }

    public Long getReviewId() {
        return reviewId;
    }

    public void setReviewId(Long reviewId) {
        this.reviewId = reviewId;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public User getUsername() {
        return username;
    }

    public void setUsername(User username) {
        this.username = username;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return "ReviewsRatings{" +
                "reviewId=" + reviewId +
                ", review='" + review + '\'' +
                ", rating=" + rating +
                ", date=" + date +
                ", username=" + username +
                ", item=" + item +
                '}';
    }
}
