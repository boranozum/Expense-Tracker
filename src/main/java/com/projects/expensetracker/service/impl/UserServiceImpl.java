package com.projects.expensetracker.service.impl;

import com.projects.expensetracker.dto.UserDto;
import com.projects.expensetracker.exceptions.UserNotFoundException;
import com.projects.expensetracker.model.User;
import com.projects.expensetracker.repository.UserRepository;
import com.projects.expensetracker.service.UserService;
import com.projects.expensetracker.utils.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<UserDto> findAllUsers() {
        return ObjectMapper.mapAll(userRepository.findAll(), UserDto.class);
    }

    @Override
    public UserDto findUserById(Long id) throws UserNotFoundException {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public User getUserByID(Long id) throws UserNotFoundException {
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
    public Boolean deleteUser(Long userId) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            userRepository.deleteById(userId);
            return true;
        } else {
            throw new UserNotFoundException("User not found");
        }
    }

    @Override
    public void createUser(UserDto userDto) {

        User newUser = modelMapper.map(userDto, User.class);
        userRepository.save(newUser);
    }



}
