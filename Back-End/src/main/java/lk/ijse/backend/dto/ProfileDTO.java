package lk.ijse.backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.stereotype.Component;

@Component
public class ProfileDTO {
    @Email(message = "invalid format")
    private String email;
    private String image;
    @Size(min = 2,max = 30,message = "Name min length is 2  ")
    private String name;
    @Size(min = 5,max = 30,message = "Name min length is 5  ")
    private String address;
    @Pattern(regexp = "^[0-9]{10}$",message = "Phone number must be 10 digits")
        private String contact;

    public ProfileDTO() {
    }

    public ProfileDTO(String email, String image, String name, String address, String contact) {
        this.email = email;
        this.image = image;
        this.name = name;
        this.address = address;
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
        return "ProfileDTO{" +
                "email='" + email + '\'' +
                ", image='" + image + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", contact='" + contact + '\'' +
                '}';
    }
}
