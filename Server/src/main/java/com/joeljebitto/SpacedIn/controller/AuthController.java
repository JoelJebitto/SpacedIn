package com.joeljebitto.SpacedIn.controller;

import com.joeljebitto.SpacedIn.dto.AuthResponse;
import com.joeljebitto.SpacedIn.dto.LoginRequest;
import com.joeljebitto.SpacedIn.dto.RegisterRequest;
import com.joeljebitto.SpacedIn.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody RegisterRequest request) {
        authService.register(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
}
