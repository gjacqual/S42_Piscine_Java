package edu.school21.models;

import java.util.Objects;

public class User {
    private Long identifier;
    private String login;
    private String password;
    private Boolean isAuthenticated;

    public User(Long identifier, String login, String password, Boolean isAuthenticated) {
        this.identifier = identifier;
        this.login = login;
        this.password = password;
        this.isAuthenticated = isAuthenticated;
    }

    public Long getIdentifier() {
        return identifier;
    }

    public void setIdentifier(Long identifier) {
        this.identifier = identifier;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getAuthenticated() {
        return isAuthenticated;
    }

    public void setAuthenticated(Boolean authenticated) {
        isAuthenticated = authenticated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return getIdentifier().equals(user.getIdentifier()) && getLogin().equals(user.getLogin()) && getPassword().equals(user.getPassword()) && isAuthenticated.equals(user.isAuthenticated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdentifier(), getLogin(), getPassword(), isAuthenticated);
    }

    @Override
    public String toString() {
        return "User{" +
                "identifier=" + identifier +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", isAuthenticated=" + isAuthenticated +
                '}';
    }
}
