package com.marcuschok.UnifiedBackend.service;

import com.marcuschok.UnifiedBackend.model.User;
import com.marcuschok.UnifiedBackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        return this.userRepository.findAll();
    }

    public void addNewUser(User user) {
        Optional<User> optionalUser = this.userRepository.findByUsername(user.getUsername());
        if (optionalUser.isPresent()) {
            throw new IllegalStateException("Username taken");
        }
        this.userRepository.save(user);
    }

    public User findUserByUsername(String username) {
        Optional<User> user = this.userRepository.findByUsername(username);
        if (user.isEmpty()) {
            throw new IllegalStateException("Username not found");
        }
        return user.get();
    }

    @Transactional
    public void updateUserByUsername(User newUser, String username) {
        User user = this.findUserByUsername(username);

        String newUsername = newUser.getUsername();
        String newPassword = newUser.getPassword();

        if (this.userRepository.findByUsername(newUsername).isPresent()) {
            throw new IllegalStateException("Username taken");
        }

        if (newUsername != null && newUsername.length() > 0) {
            user.setUsername(newUser.getUsername());
        }
        if (newPassword != null && newPassword.length() > 0) {
            user.setPassword(newUser.getPassword());
        }
    }

    public void deleteUserByUsername(String username) {
        if (this.userRepository.findByUsername(username).isEmpty()) return;

        this.userRepository.deleteByUsername(username);
    }
}
