package com.example.projects3java.model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {

     @NotEmpty(message = "ID cannot be empty")
    private String UserId;

     @NotEmpty(message = "Username cannot be empty")
    @Size(min = 6 )
    private String username;

     @NotEmpty(message = "Username cannot be empty")
     @Size(min = 7 )
     @Pattern(regexp ="^[A-Za\\s]{1,}[0-9\\s]{1,}$", message = "Please enter a valid password")
    private String password;

     @NotEmpty(message = "Email cannot be empty")
     @Email
    private String email;

     @NotEmpty(message = "Role cannot be empty")
     @Pattern(regexp = "(admin)|(customer)|(Admin)|(Customer)", message = "Role must be admin or customer only")
    private String role;

     @NotNull(message = "Balance cannot be empty")
     @PositiveOrZero(message = "Balance must be a positive number")
    private Double balance;
}
