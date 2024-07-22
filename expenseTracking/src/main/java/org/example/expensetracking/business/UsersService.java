package org.example.expensetracking.business;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.expensetracking.dataAccess.UsersRepository;
import org.example.expensetracking.entities.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsersService {

    private UsersRepository usersRepository;

    public Users createUser(Users user) {
        return usersRepository.save(user);
    }

    public Optional<Users> getUserById(Long id) {
        return usersRepository.findById(id);
    }

    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }

    public Users updateUser(Long id, Users userDetails) {
        Users user = usersRepository.findById(id).orElseThrow();
        user.setUsername(userDetails.getUsername());
        user.setEmail(userDetails.getEmail());
        user.setPassword(userDetails.getPassword());
        return usersRepository.save(user);
    }

    public void deleteUser(Long id) {
        usersRepository.deleteById(id);
    }
}
