package com.joeljebitto.SpacedIn.service;

import com.joeljebitto.SpacedIn.dto.UserDTO;
import com.joeljebitto.SpacedIn.entity.User;
import com.joeljebitto.SpacedIn.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder = new BCryptPasswordEncoder();

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUser(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    public UserDTO updateUser(Long id, UserDTO userData) {
        User user = getUser(id);
        user.setEmail(userData.getEmail());
        user.setUsername(userData.getUsername());
        return new UserDTO(userRepository.save(user));
    }

    public void changePassword(Long id, String newPassword) {
        User user = getUser(id);
        user.setPassword(encoder.encode(newPassword));
        userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
