package lk.ijse.backend.dto;

import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Size;
import lk.ijse.backend.entity.Item;
import lk.ijse.backend.entity.User;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class ReviewsRatingsDTO {
    private Long reviewId;
    @Size(max = 1000)
    private String review;
    @Size(min = 1,max = 5)
    private int rating;
    @Column(updatable = false)
    @CreationTimestamp
    private Timestamp date;
    private UserDTO username;
    private ItemDTO itemCode;

    public ReviewsRatingsDTO() {
    }

    public ReviewsRatingsDTO(Long reviewId, String review, int rating, Timestamp date, UserDTO username, ItemDTO itemCode) {
        this.reviewId = reviewId;
        this.review = review;
        this.rating = rating;
        this.date = date;
        this.username = username;
        this.itemCode = itemCode;
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

    public UserDTO getUsername() {
        return username;
    }

    public void setUsername(UserDTO username) {
        this.username = username;
    }

    public ItemDTO getItemCode() {
        return itemCode;
    }

    public void setItemCode(ItemDTO itemCode) {
        this.itemCode = itemCode;
    }

    @Override
    public String toString() {
        return "ReviewsRatingsDTO{" +
                "reviewId=" + reviewId +
                ", review='" + review + '\'' +
                ", rating=" + rating +
                ", date=" + date +
                ", username=" + username +
                ", itemCode=" + itemCode +
                '}';
    }
}
