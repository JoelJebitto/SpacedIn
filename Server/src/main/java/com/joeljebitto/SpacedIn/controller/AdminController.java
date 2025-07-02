package com.joeljebitto.SpacedIn.controller;

import com.joeljebitto.SpacedIn.dto.UserDTO;
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
    public ResponseEntity<List<UserDTO>> getUsers() {
        List<UserDTO> users = userRepository.findAll().stream()
                .map(UserDTO::new)
                .toList();
        return ResponseEntity.ok(users);
    }

    @DeleteMapping("/deck/{id}")
    public ResponseEntity<Void> deleteDeck(@PathVariable Long id) {
        adminService.deleteDeck(id);
        return ResponseEntity.ok().build();
    }
}
