package school21.spring.service.models;

import java.util.Objects;

public class User {
    private Long identifier;
    private String email;

    private String password;

    public User(Long identifier, String email, String password) {
        this.identifier = identifier;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return getIdentifier().equals(user.getIdentifier()) && getEmail().equals(user.getEmail()) && getPassword().equals(user.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdentifier(), getEmail(), getPassword());
    }

    @Override
    public String toString() {
        return "User{" +
                "identifier=" + identifier +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
