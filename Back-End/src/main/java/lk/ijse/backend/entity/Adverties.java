package lk.ijse.backend.entity;

import jakarta.persistence.*;

@Entity
public class Adverties {
    @Id
    private String advertiesId;
    @ManyToOne
    private User userId;
    private String itemId;



}
