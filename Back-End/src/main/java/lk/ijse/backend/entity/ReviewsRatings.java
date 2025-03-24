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
    private Item itemCode;
}
