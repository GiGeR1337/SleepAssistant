package com.example.sleepproject.DTOs;

import com.example.sleepproject.Constrains.UniqueEmail;
import com.example.sleepproject.Constrains.UniqueUsername;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserDto {
    @NotBlank(message = "Username must not be blank")
    @Size(min = 2, max = 32, message = "Username must be between 2 and 32 characters long")
    @UniqueUsername(message = "This username is already in use")
    private String username;
    @Email
    @NotBlank(message = "Email must not be blank")
    @UniqueEmail(message = "This email address is already in use.")
    private String email;
    @NotBlank(message = "Password must not be blank")
    @Size(min = 6, max = 64, message = "Password must be between 6 and 64 characters long")
    private String password;

    public UserDto() {
    }

    public UserDto(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
