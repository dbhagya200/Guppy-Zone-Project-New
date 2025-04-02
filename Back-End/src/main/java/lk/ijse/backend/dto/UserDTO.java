package lk.ijse.backend.dto;

import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class UserDTO {
    private String username;
    private String email;
    private String password;
    private String role;
    private LocalDate joinDate;

    public UserDTO() {
    }

    public UserDTO(String username, String email, String password, String role, LocalDate joinDate) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
        this.joinDate = joinDate;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public LocalDate getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(LocalDate joinDate) {
        this.joinDate = joinDate;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", joinDate='" + joinDate + '\'' +
                '}';
    }
}
