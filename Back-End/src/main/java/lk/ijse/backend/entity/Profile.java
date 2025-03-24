package lk.ijse.backend.entity;

import jakarta.persistence.*;

@Entity
public class Profile {
    @Id
    private String profileId;
    @OneToOne
    private User userId;
    private String image;
    private String name;
    private String address;
    private String contact;


}
