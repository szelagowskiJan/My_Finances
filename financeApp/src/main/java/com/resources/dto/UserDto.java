package com.resources.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import lombok.RequiredArgsConstructor;

@Getter
@Setter
@RequiredArgsConstructor
public class UserDto {

    private int userId;
    
    @NotEmpty
    private String firstName;
   
    @NotEmpty
    private String lastName;
   
    @NotEmpty(message = "Email should not be empty")
    @Email
    private String email;
   
    @NotEmpty(message = "Password should not be empty")
    private String password;
    
    @NotEmpty(message = "Confirm password should not be empty")
    private String confirmPassword;
    
    private int balance;
    
    private int userRole;

    private String verificationCode;

    private boolean enabled;

    public String getFullName()
    {
        return firstName + " " + lastName;
    }
}