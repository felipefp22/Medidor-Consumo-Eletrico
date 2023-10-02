package br.com.fiap.techchallenge.infra.login;

public enum UserRole {

    ADMIN("admin"),
    USER("user");

    private String role;

    UserRole(String role){
        this.role = role;
    }

    public String getRole() {
        return role;
    }
    public UserRole setRole(String role) {
        this.role = role;
        return this;
    }
}
