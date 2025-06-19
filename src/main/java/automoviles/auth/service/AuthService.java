package automoviles.auth.service;

import automoviles.auth.dto.AuthResponse;
import automoviles.auth.dto.AuthRequest;
import automoviles.auth.dto.RegisterRequest;
import automoviles.auth.dto.UpdateUserRequest;
import automoviles.auth.entity.User;

public interface AuthService {

    AuthResponse login(AuthRequest request);
    AuthResponse register(RegisterRequest request);
    User getUserById(Long id);
    AuthResponse updateUser(Long id, UpdateUserRequest request);
    void deleteUser(Long id);
}