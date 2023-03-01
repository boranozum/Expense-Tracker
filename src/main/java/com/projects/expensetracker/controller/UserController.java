package com.projects.expensetracker.controller;

import com.projects.expensetracker.exceptions.UserNotFoundException;
import com.projects.expensetracker.model.User;
import com.projects.expensetracker.service.UserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    // constructor injection
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getUsers() {
        return userService.findAllUsers();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) throws UserNotFoundException {
        return userService.findUserById(id);
    }

    @PostMapping
    public void createUser(@RequestBody @Validated User user) {
        User newUser = new User(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword());
        userService.createUser(newUser);
    }

    @PutMapping("/{userId}")
    public void updateUser(@PathVariable("userId") Long userId, @RequestBody @Validated User user) throws UserNotFoundException {

        userService.updateUser(userId, user);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable("userId") Long userId) throws UserNotFoundException {
        // make custom exception
        userService.deleteUser(userId);
    }

}
