package com.joeljebitto.SpacedIn.controller;

import com.joeljebitto.SpacedIn.dto.UserDTO;
import com.joeljebitto.SpacedIn.entity.User;
import com.joeljebitto.SpacedIn.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> get(@PathVariable Long id) {
        return ResponseEntity.ok(new UserDTO(userService.getUser(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> update(@PathVariable Long id, @RequestBody UserDTO user) {
        User entity = new User();
        entity.setEmail(user.getEmail());
        entity.setUsername(user.getUsername());
        User updated = userService.updateUser(id, entity);
        return ResponseEntity.ok(new UserDTO(updated));
    }

    @PostMapping("/change-password")
    public ResponseEntity<Void> changePassword(@RequestParam Long id, @RequestParam String password) {
        userService.changePassword(id, password);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
}
