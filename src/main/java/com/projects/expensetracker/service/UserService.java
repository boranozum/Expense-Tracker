package com.projects.expensetracker.service;

import com.projects.expensetracker.exceptions.UserNotFoundException;
import com.projects.expensetracker.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {


    List<User> findAllUsers();

    User findUserById(Long id) throws UserNotFoundException;

    void updateUser(Long userId, User user) throws UserNotFoundException;

    void deleteUser(Long userId) throws UserNotFoundException;

    void createUser(User user);
}
