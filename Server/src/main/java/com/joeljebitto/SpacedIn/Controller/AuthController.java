package com.joeljebitto.SpacedIn.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joeljebitto.SpacedIn.DTO.AuthRequest;
import com.joeljebitto.SpacedIn.DTO.AuthResponse;
import com.joeljebitto.SpacedIn.Entity.User;
import com.joeljebitto.SpacedIn.Repository.UserRepository;
import com.joeljebitto.SpacedIn.Utils.JwtUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {
  @Autowired
  private UserRepository userRepo;
  @Autowired
  private PasswordEncoder encoder;
  @Autowired
  private AuthenticationManager authManager;
  @Autowired
  private JwtUtil jwtUtil;

  @PostMapping("/signup")
  public ResponseEntity<String> signup(@RequestBody AuthRequest request) {
    if (userRepo.findByUsername(request.username()).isPresent()) {
      return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already exists");
    }
    User user = new User();
    user.setUsername(request.username());
    user.setPassword(encoder.encode(request.password()));
    user.setRole("ROLE_USER");
    userRepo.save(user);
    return ResponseEntity.ok("User registered successfully");
  }

  @PostMapping("/login")
  public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
    authManager.authenticate(
        new UsernamePasswordAuthenticationToken(request.username(), request.password()));
    String token = jwtUtil.generateToken(request.username());
    return ResponseEntity.ok(new AuthResponse(token));
  }
}
