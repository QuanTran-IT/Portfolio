package org.quan.portfolio.infomation.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ContactForm {

    @NotBlank(message = "Please enter your email")
    @Email(message = "Invalid email address")
    private String email;

    @NotBlank(message = "Please enter a message")
    @Size(max = 2000, message = "Message must be at most 2000 characters")
    private String message;

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}