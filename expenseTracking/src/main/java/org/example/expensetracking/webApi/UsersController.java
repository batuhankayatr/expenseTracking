package org.example.expensetracking.webApi;

import org.example.expensetracking.business.UsersService;
import org.example.expensetracking.entities.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UsersController {
    @Autowired
    private UsersService usersService;

    @PostMapping
    public Users createUser(@RequestBody Users user) {
        return usersService.createUser(user);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Users> getUserById(@PathVariable Long id) {
        return usersService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Users> getAllUsers() {
        return usersService.getAllUsers();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Users> updateUser(@PathVariable Long id, @RequestBody Users userDetails) {
        return ResponseEntity.ok(usersService.updateUser(id, userDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        usersService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
