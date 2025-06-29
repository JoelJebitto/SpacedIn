package com.joeljebitto.SpacedIn.service;

import com.joeljebitto.SpacedIn.dto.AuthResponse;
import com.joeljebitto.SpacedIn.dto.LoginRequest;
import com.joeljebitto.SpacedIn.dto.RegisterRequest;
import com.joeljebitto.SpacedIn.entity.User;
import com.joeljebitto.SpacedIn.repository.UserRepository;
import com.joeljebitto.SpacedIn.security.JwtUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public AuthService(UserRepository userRepository, JwtUtils jwtUtils) {
        this.userRepository = userRepository;
        this.jwtUtils = jwtUtils;
    }

    public void register(RegisterRequest req) {
        User user = new User();
        user.setUsername(req.getUsername());
        user.setPassword(passwordEncoder.encode(req.getPassword()));
        user.setEmail(req.getEmail());
        userRepository.save(user);
    }

    public AuthResponse login(LoginRequest req) {
        User user = userRepository.findByUsername(req.getUsername()).orElseThrow();
        if (passwordEncoder.matches(req.getPassword(), user.getPassword())) {
            String token = jwtUtils.generateToken(user);
            return new AuthResponse(token);
        }
        throw new RuntimeException("Invalid credentials");
    }
}
