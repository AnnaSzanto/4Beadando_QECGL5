package com.anna.beadando.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserCredential {
    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;

    public boolean isUsernameValid() {
        if (this.username.length() >= 6) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isPasswordValid() {
        if(this.password.length() < 8) {
            return false;
        } else if (this.password.matches(".*[0-9].*")) {
            return false;
        } else if (this.password.matches(".*[a-zA-Z].*")) {
            return false;
        } else {
            return true;
        }
    }
}