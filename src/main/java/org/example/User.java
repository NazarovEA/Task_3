package org.example;

public class User {
    private String email;
    private String password;
    private String name;

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
    // Геттеры и сеттеры (или используй Lombok @Data)
    public String getEmail() { return email; }
    public String getPassword() { return password; }
}