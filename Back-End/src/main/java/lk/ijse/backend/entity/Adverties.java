package lk.ijse.backend.entity;

import jakarta.persistence.*;

@Entity
public class Adverties {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int advertiesId;
    @ManyToOne
    private User userId;
    private String itemId;



}
