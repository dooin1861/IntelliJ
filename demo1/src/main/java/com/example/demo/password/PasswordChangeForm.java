package com.example.demo.password;

import lombok.Data;

@Data
public class PasswordChangeForm {
    private String email;
    private String oldPassword;
    private String newPassword;
    private String confirmPassword;
}
