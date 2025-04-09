package com.petr.postcode_api.user;

import com.petr.postcode_api.common.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "users")
public class User extends BaseEntity {
    @Column
    @NotEmpty(message = "First name is required.")
    private String firstName;

    @Column
    @NotEmpty(message = "Last name is required.")
    private String lastName;

    @Column(unique = true)
    @NotEmpty(message = "Email is required.")
    @Email
    private String email;

    @Column
    @NotEmpty(message = "Username is required.")
    private String username;

    @Column
    @NotEmpty(message = "Password is required")
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    public User() {}

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", username=" + username
                + ", password=" + password + ", role=" + role + "]";
    }
}
