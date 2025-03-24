package lk.ijse.backend.dto;

import org.springframework.web.multipart.MultipartFile;

public class ProfileDataDTO {
    private String firstName;
    private String lastName;
    private MultipartFile image;
    private String email;
    private String contact;
    private String address;

    public ProfileDataDTO() {
    }

    public ProfileDataDTO(String firstName, String lastName, MultipartFile image, String email, String contact, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.image = image;
        this.email = email;
        this.contact = contact;
        this.address = address;
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

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "profileDataDTO{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", image=" + image +
                ", email='" + email + '\'' +
                ", contact='" + contact + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
