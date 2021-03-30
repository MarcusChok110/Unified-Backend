package com.marcuschok.UnifiedBackend.controller;

import com.marcuschok.UnifiedBackend.model.User;
import com.marcuschok.UnifiedBackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getUsers() {
        return this.userService.getUsers();
    }

    @PostMapping
    public void addUser(@RequestBody User user) {
        this.userService.addNewUser(user);
    }

    @GetMapping("/{username}")
    public User getUser(@PathVariable("username") String username) {
        return this.userService.findUserByUsername(username);
    }

    @PutMapping("/{username}")
    public void updateUser(@RequestBody User user, @PathVariable("username") String username) {
        this.userService.updateUserByUsername(user, username);
    }

    @DeleteMapping("/{username}")
    public void deleteUser(@PathVariable("username") String username) {
        this.userService.deleteUserByUsername(username);
    }
}
