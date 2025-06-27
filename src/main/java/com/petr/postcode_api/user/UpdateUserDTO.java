package com.petr.postcode_api.user;

import jakarta.validation.constraints.Pattern;

public class UpdateUserDTO {
    @Pattern(regexp = "\\S+.*", message = "First name is required.")
    private String firstName;

    @Pattern(regexp = "\\S+.*", message = "Last name is required.")
    private String lastName;

    @Pattern(regexp = "\\S+.*", message = "Email is required.")
    private String email;

    @Pattern(regexp = "\\S+.*", message = "Username is required.")
    private String username;

    /////////// SHOULD NOT HAVE PASSWORD FEILD //////////////
    // @Pattern(regexp = "\\S+.*", message = "Password is required.")
    // private String password;

    private Role role;

    public UpdateUserDTO() {}

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

    // public String getPassword() {
    //     return password;
    // }

    // public void setPassword(String password) {
    //     this.password = password;
    // }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UpdateUserDTO [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", username="
                + username + ", role=" + role + "]";
    }

}
