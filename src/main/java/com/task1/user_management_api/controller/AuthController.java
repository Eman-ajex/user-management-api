package com.task1.user_management_api.controller;
import com.task1.user_management_api.dto.LoginRequest;
import com.task1.user_management_api.dto.RegisterRequest;
import com.task1.user_management_api.entity.User;
import com.task1.user_management_api.repository.UserRepository;
import com.task1.user_management_api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.task1.user_management_api.security.JwtService;
import com.task1.user_management_api.dto.AuthResponse;
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController{

    private final UserService userService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("Username already exists");
        }
        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .name(request.getName())
                .build();
        return ResponseEntity.ok(userService.save(user));
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElse(null);
        if (user == null || !passwordEncoder.matches(request.getPassword(),
                user.getPassword())){
            return ResponseEntity.badRequest().body("Invalid credentials");
        }
        String token =

                jwtService.generateToken(

                        user.getUsername()

                );

        return ResponseEntity.ok(

                new AuthResponse(token)

        );

    }
}
