package automoviles.controller;

import automoviles.auth.entity.User;
import automoviles.auth.repository.UserRepository;
import automoviles.dto.request.UsuarioRequest;
import automoviles.dto.response.UsuarioResponse;
import automoviles.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
    private UserRepository userRepository;

    @PostMapping ("/create")// crear un usuario
    public ResponseEntity<UsuarioResponse> crearUsuario(@RequestBody UsuarioRequest request) { 
        return ResponseEntity.ok(usuarioService.crearUsuario(request));
    }

    @GetMapping("/{id}") // obtener un usuario por su id
    public ResponseEntity<UsuarioResponse> obtenerUsuarioPorId(@PathVariable Long id) {
       return ResponseEntity.ok(usuarioService.obtenerUsuarioPorId(id));
    }

    @GetMapping("/todos")// obtener todos los usuarios
    public ResponseEntity<Collection<UsuarioResponse>> obtenerTodosLosUsuarios() {
       return ResponseEntity.ok(usuarioService.obtenerTodosLosUsuarios());
    }
    
    @GetMapping("/users-disponibles")// obtener todos los Users disponibles
    public ResponseEntity<List<Object>> obtenerUsersDisponibles() {
        List<User> users = userRepository.findAll();
        List<Object> usersSimplificados = users.stream()
            .map(user -> {
                return Map.of(
                    "id", user.getId(),
                    "username", user.getUsername(),
                    "email", user.getEmail(),
                    "rol", user.getRol()
                );
            })
            .collect(Collectors.toList());
        return ResponseEntity.ok(usersSimplificados);
    }

    @PutMapping("/update/{id}")// actualizar un usuario por id
    public ResponseEntity<UsuarioResponse> actualizarUsuarioId(@PathVariable Long id, @RequestBody UsuarioRequest request) {
        return ResponseEntity.ok(usuarioService.actualizarUsuario(id, request));
    }

    @DeleteMapping("/delete/{id}") // eliminar un usuario  por id
    public void eliminarUsuarioId(@PathVariable Long id) {
        usuarioService.eliminarUsuario(id);
    }

    @GetMapping("/mis-datos")
    public ResponseEntity<UsuarioResponse> getMisDatos(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return ResponseEntity.ok(usuarioService.obtenerUsuarioPorId(user.getId()));
    }

}