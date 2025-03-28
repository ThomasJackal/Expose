package fr.eql.ai116.duron.thomas.art.connect.security.entity.dto;

import fr.eql.ai116.duron.thomas.art.connect.security.entity.RoleName;

public class RegistrationDto {

    private String username;
    private String password;
    private RoleName roleName;

    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public RoleName getRoleName() {
        return roleName;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setRoleName(RoleName roleName) {
        this.roleName = roleName;
    }
}
