package com.projects.expensetracker.dto;

import com.projects.expensetracker.security.PasswordMatches;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
@PasswordMatches
public class RegisterRequest {

        @NotNull
        @NotEmpty
        private String firstName;
        @NotNull
        @NotEmpty
        private String lastName;

        @Email
        @NotNull
        @NotEmpty
        private String email;

        @NotNull
        @NotEmpty
        @Length(min = 8)
        private String password;
        private String confirmPassword;


        private Double monthlyBudget;
}
