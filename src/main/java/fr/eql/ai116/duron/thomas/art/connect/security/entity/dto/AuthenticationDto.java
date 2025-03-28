package fr.eql.ai116.duron.thomas.art.connect.security.entity.dto;

public class AuthenticationDto {

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
