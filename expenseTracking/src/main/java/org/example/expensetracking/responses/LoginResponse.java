package org.example.expensetracking.responses;

import lombok.Data;

@Data
public class LoginResponse {
    private Long userId;
    private String username;
    private String email;
}
