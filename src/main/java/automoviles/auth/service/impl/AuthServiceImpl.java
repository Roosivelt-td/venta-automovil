package automoviles.auth.service.impl;

import automoviles.auth.dto.AuthResponse;
import automoviles.auth.dto.AuthRequest;
import automoviles.auth.dto.RegisterRequest;
import automoviles.auth.dto.UpdateUserRequest;
import automoviles.auth.entity.Rol;
import automoviles.auth.entity.User;
import automoviles.auth.repository.UserRepository;
import automoviles.auth.security.JwtService;
import automoviles.auth.service.AuthService;
import automoviles.model.Usuario;
import automoviles.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final UsuarioRepository usuarioRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }


    @Override
    @Transactional

    public AuthResponse register(RegisterRequest request) {
        var user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .rol(Rol.USER)
                .build();
        userRepository.save(user);
        // 2. Crear Usuario (datos personales)
        Usuario usuario = Usuario.builder()
                .nombre(request.getNombre())
                .apellido(request.getApellido())
                .sexo(request.getSexo())
                .direccion(request.getDireccion())
                .celular(request.getCelular())
                .estado(true) // Por defecto activo
                .user(user)
                .build();

        usuarioRepository.save(usuario); // CORRECTO

        // 3. Actualizar relación bidireccional
        user.setUsuarioDetalles(usuario);
        userRepository.save(user);

        var jwtToken = jwtService.generateToken(user);
        return AuthResponse.builder().token(jwtToken).build();
    }

    @Override
    public AuthResponse login(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByUsername(request.getUsername())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthResponse.builder()
                .token(jwtToken)
                .build();
    }

    @Override
    @Transactional
    public AuthResponse updateUser(Long id, UpdateUserRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        userRepository.save(user);

        String newToken = jwtService.generateToken(user);
        return AuthResponse.builder().token(newToken).build();
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
