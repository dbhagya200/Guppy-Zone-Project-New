package lk.ijse.backend.entity;
import jakarta.persistence.*;


@Entity
public class Role {
    @Id
    private String roleId;
    private String roleName;
    private String privileges;
    @ManyToOne
    private User user;

    public Role() {
    }

    public Role(String roleId, String roleName, String privileges, User user) {
        this.roleId = roleId;
        this.roleName = roleName;
        this.privileges = privileges;
        this.user = user;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getPrivileges() {
        return privileges;
    }

    public void setPrivileges(String privileges) {
        this.privileges = privileges;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleId='" + roleId + '\'' +
                ", roleName='" + roleName + '\'' +
                ", privileges='" + privileges + '\'' +
                ", user=" + user +
                '}';
    }
}
