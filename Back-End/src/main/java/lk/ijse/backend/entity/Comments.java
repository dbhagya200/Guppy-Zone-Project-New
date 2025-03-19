package lk.ijse.backend.entity;

import jakarta.persistence.*;

@Entity
public class Comments {
    @Id
    private String commentId;
    private String userId;
    private String advertiesId;
    private String comment;
}
