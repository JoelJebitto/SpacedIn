package com.joeljebitto.SpacedIn.controller;

import com.joeljebitto.SpacedIn.entity.User;
import com.joeljebitto.SpacedIn.repository.UserRepository;
import com.joeljebitto.SpacedIn.service.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    private final UserRepository userRepository;
    private final AdminService adminService;

    public AdminController(UserRepository userRepository, AdminService adminService) {
        this.userRepository = userRepository;
        this.adminService = adminService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    @DeleteMapping("/deck/{id}")
    public ResponseEntity<Void> deleteDeck(@PathVariable Long id) {
        adminService.deleteDeck(id);
        return ResponseEntity.ok().build();
    }
}
