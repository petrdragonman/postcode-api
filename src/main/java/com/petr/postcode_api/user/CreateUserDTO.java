package com.petr.postcode_api.user;

import jakarta.validation.constraints.NotEmpty;

public class CreateUserDTO {
    @NotEmpty(message = "First name is required.")
    private String firstName;

    @NotEmpty(message = "Last name is required.")
    private String lastName;

    @NotEmpty(message = "Email is required.")
    private String email;

    @NotEmpty(message = "Username is required.")
    private String username;

    /////////// SHOULD NOT HAVE PASSWORD FEILD //////////////
    @NotEmpty(message = "Password is required.")
    private String password;

    private Role role;

    public CreateUserDTO() {}

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    @Override
    public String toString() {
        return "CreateUserDTO [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", username="
                + username + ", role=" + role + "]";
    }
}
