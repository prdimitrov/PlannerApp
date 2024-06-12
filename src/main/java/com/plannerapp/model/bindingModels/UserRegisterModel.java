package com.plannerapp.model.bindingModels;

import com.plannerapp.util.validation.FieldsMatch;
import com.plannerapp.util.validation.UniqueUserEmail;
import com.plannerapp.util.validation.UniqueUserName;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


 @FieldsMatch(first = "password",
 second = "confirmPassword",
 message = "Passwords do not match.")
public class UserRegisterModel {

    @NotNull
    @Size(min = 3, max = 20, message = "Username length must be between 3 and 20 characters!")
    @UniqueUserName(message = "Username is occupied!")
    private String username;

    @NotEmpty(message = "Email cannot be empty")
    @Email
    @UniqueUserEmail(message = "Email is already in use!")
    private String email;

    @NotNull
    @Size(min = 3, max = 20, message = "Password length must be between 3 and 20 characters!")
    private String password;

    @NotNull
    @Size(min = 3, max = 20)
    private String confirmPassword;

    public UserRegisterModel() {
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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
