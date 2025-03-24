package lk.ijse.backend.dto;

import jakarta.validation.constraints.NotBlank;

public class RoleDTO {
    private String roleId;
    @NotBlank(message = "Role name is required")
    private String roleName;
    private String privileges;

    public RoleDTO() {
    }

    public RoleDTO(String roleId, String roleName, String privileges) {
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

    @Override
    public String toString() {
        return "RoleDTO{" +
                "roleId='" + roleId + '\'' +
                ", roleName='" + roleName + '\'' +
                ", privileges='" + privileges + '\'' +
                '}';
    }
}
