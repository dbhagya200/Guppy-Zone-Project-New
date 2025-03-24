package lk.ijse.backend.entity;

import jakarta.persistence.*;

@Entity
public class Profile {
    @Id
    private String profileId;
    @OneToOne
    @JoinColumn(name = "email", referencedColumnName = "email")
    private User user;
    private String image;
    private String name;
    private String address;
    private String contact;

    public Profile() {
    }

    public Profile(String profileId, User userId, String image, String name, String address, String contact) {
        this.profileId = profileId;
        this.user = userId;
        this.image = image;
        this.name = name;
        this.address = address;
        this.contact = contact;
    }

    public String getProfileId() {
        return profileId;
    }

    public void setProfileId(String profileId) {
        this.profileId = profileId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User userId) {
        this.user = userId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return "Profile{" +
                "profileId='" + profileId + '\'' +
                ", userId=" + user +
                ", image='" + image + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", contact='" + contact + '\'' +
                '}';
    }
}
