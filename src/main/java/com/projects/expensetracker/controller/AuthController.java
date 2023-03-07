package com.projects.expensetracker.controller;

import com.projects.expensetracker.dto.LoginRequest;
import com.projects.expensetracker.dto.LoginResponse;
import com.projects.expensetracker.dto.RegisterRequest;
import com.projects.expensetracker.dto.UserDto;
import com.projects.expensetracker.exceptions.UserNotFoundException;
import com.projects.expensetracker.model.User;
import com.projects.expensetracker.security.JwtTokenProvider;
import com.projects.expensetracker.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    private final UserService userService;

    private final PasswordEncoder passwordEncoder;

    public AuthController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Valid RegisterRequest registerRequest) {

        try {
            User user = userService.getUserByEmail(registerRequest.getEmail());
            return ResponseEntity.badRequest().body("User already exists");
        } catch (UserNotFoundException e) {

            registerRequest.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
            userService.createUser(registerRequest);
            return ResponseEntity.ok("User registered successfully");
        }
    }

    @PostMapping ("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) throws UserNotFoundException {

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);

        SecurityContextHolder.getContext().setAuthentication(authenticate);

        return ResponseEntity.ok(new LoginResponse(jwtTokenProvider.generateToken(authenticate), userService.getUserByEmail(loginRequest.getEmail()).getId()));
    }
}
