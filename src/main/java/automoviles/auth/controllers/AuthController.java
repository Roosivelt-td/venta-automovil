package automoviles.auth.controllers;

import automoviles.auth.dto.AuthResponse;
import automoviles.auth.dto.AuthRequest;
import automoviles.auth.dto.RegisterRequest;
import automoviles.auth.dto.UpdateUserRequest;
import automoviles.auth.service.AuthService;
import automoviles.auth.service.TokenBlacklistService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final TokenBlacklistService tokenBlacklistService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        String token = extractToken(request);
        tokenBlacklistService.addToBlacklist(token);
        return ResponseEntity.ok("Sesión cerrada exitosamente");
    }

    private String extractToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer ")) {
            throw new IllegalArgumentException("Token no proporcionado o formato inválido");
        }
        return header.substring(7);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        authService.deleteUser(id);
        return ResponseEntity.ok("Usuario eliminado exitosamente");
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<AuthResponse> updateUser(
            @PathVariable Long id,
            @RequestBody UpdateUserRequest request) {
        return ResponseEntity.ok(authService.updateUser(id, request));
    }
}