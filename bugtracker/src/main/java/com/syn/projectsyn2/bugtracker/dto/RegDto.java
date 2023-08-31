package com.syn.projectsyn2.bugtracker.dto;

import com.syn.projectsyn2.bugtracker.domain.Role.RoleName;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * DTO (Data Transfer Object) for the User entity, specifically for registration.
 * This DTO will help in transporting data from the client side to the server 
 * without exposing all the underlying entity details, especially for the purpose of registration.
 */
public class RegDto {
    
    // Username for the user. This cannot be blank, and has a min and max size.
    @NotBlank(message = "Username is required.")
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters.")
    private String username;
    
    // Email for the user. This follows the email format and cannot be blank.
    @NotBlank(message = "Email is required.")
    @Email(message = "Invalid email format.")
    private String email;
    
    // Password for the user. This cannot be blank and has a min and max size.
    @NotBlank(message = "Password is required.")
    @Size(min = 8, max = 50, message = "Password must be between 8 and 50 characters.")
    private String password;

    //not stored in the database but 
    // used to ensure the user types the same password twice during registration.
    @NotBlank(message = "Password confirmation is required.")
    private String confirmPassword;

    // Standard getter for username
    public String getUsername() {
        return username;
    }

    // Standard setter for username
    public void setUsername(String username) {
        this.username = username;
    }

    // Standard getter for email
    public String getEmail() {
        return email;
    }

    // Standard setter for email
    public void setEmail(String email) {
        this.email = email;
    }

    // Standard getter for password
    public String getPassword() {
        return password;
    }

    // Standard setter for password
    public void setPassword(String password) {
        this.password = password;
    }

    // Standard getter for confirmPassword
    public String getConfirmPassword() {
        return confirmPassword;
    }

    // Standard setter for confirmPassword
    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    // Override the toString method for debugging purposes, 
    // but omit sensitive information like the password and confirmation password
    @Override
    public String toString() {
        return "RegistrationDto{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                '}';
    }


}
