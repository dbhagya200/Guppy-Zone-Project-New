package lk.ijse.backend.entity;
import jakarta.persistence.*;


@Entity
public class Role {
    @Id
    private String roleId;
    private String roleName;
    private String privileges;

    public Role() {
    }

    public Role(String roleId, String roleName, String privileges) {
        this.roleId = roleId;
        this.roleName = roleName;
        this.privileges = privileges;
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
}
