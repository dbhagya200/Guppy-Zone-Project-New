package lk.ijse.backend.dto;

import org.springframework.stereotype.Component;


@Component
public class AuthDTO {
    private String email;
    private String token;

    public AuthDTO() {
    }

    public AuthDTO(String username, String token) {
        this.email = username;
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "AuthDTO{" +
                "username='" + email + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}