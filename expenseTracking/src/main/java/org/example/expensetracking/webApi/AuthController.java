package org.example.expensetracking.webApi;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.expensetracking.business.UsersService;
import org.example.expensetracking.entities.Users;
import org.example.expensetracking.requests.LoginRequest;
import org.example.expensetracking.responses.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Tag(name = "Authentication", description = "Kullanıcı giriş işlemleri")
public class AuthController {

    @Autowired
    private UsersService usersService;

    @PostMapping("/login")
    @Operation(summary = "Kullanıcı girişi", description = "Kullanıcı adı ve şifre ile giriş yapar")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        Users user = usersService.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!loginRequest.getPassword().equals(user.getPassword())) {
            throw new RuntimeException("Incorrect password");
        }

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setUserId(user.getId());
        loginResponse.setUsername(user.getUsername());
        loginResponse.setEmail(user.getEmail());

        return ResponseEntity.ok(loginResponse);
    }
}
