package edu.school21.sockets.models;

import java.util.Objects;

public class User {
    private Long identifier;
    private String username;

    private String password;

    public User(Long identifier, String username, String password) {
        this.identifier = identifier;
        this.username = username;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getIdentifier() {
        return identifier;
    }

    public void setIdentifier(Long identifier) {
        this.identifier = identifier;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return getIdentifier().equals(user.getIdentifier()) && getUsername().equals(user.getUsername()) && getPassword().equals(user.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdentifier(), getUsername(), getPassword());
    }

    @Override
    public String toString() {
        return "User{" +
                "identifier=" + identifier +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
