package com.projects.expensetracker.service;

import com.projects.expensetracker.dto.RegisterRequest;
import com.projects.expensetracker.dto.UserDto;
import com.projects.expensetracker.exceptions.UserNotFoundException;
import com.projects.expensetracker.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {


    List<UserDto> findAllUsers();

    UserDto findUserById(Long id) throws UserNotFoundException;

    User getUserByEmail(String userName) throws UserNotFoundException;

    User getUserByID(Long id) throws UserNotFoundException;

    void updateUser(Long userId, User user) throws UserNotFoundException;

    Boolean deleteUser(Long userId) throws UserNotFoundException;

    void createUser(RegisterRequest registerRequest);
}
