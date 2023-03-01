package com.projects.expensetracker.service.impl;

import com.projects.expensetracker.exceptions.UserNotFoundException;
import com.projects.expensetracker.model.User;
import com.projects.expensetracker.repository.UserRepository;
import com.projects.expensetracker.service.UserService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findUserById(Long id) throws UserNotFoundException {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    @Override
    public void updateUser(Long userId, User user) throws UserNotFoundException {
        User existingUser = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(user.getPassword());
        userRepository.save(existingUser);
    }

    @Override
    public void deleteUser(Long userId) throws UserNotFoundException {
        User existingUser = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));
        userRepository.deleteById(userId);
    }

    @Override
    public void createUser(User user) {
        User newUser = new User(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword());
        userRepository.save(newUser);
    }



}
