package automoviles.service.impl;

import automoviles.auth.entity.User;
import automoviles.auth.repository.UserRepository;
import automoviles.dto.request.UsuarioRequest;
import automoviles.dto.response.UsuarioResponse;
import automoviles.model.Usuario;
import automoviles.repository.UsuarioRepository;
import automoviles.service.UsuarioService;
import automoviles.service.mapper.UsuarioMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    private UserRepository userRepository;

    @Autowired
    private UsuarioMapper usuarioMapper;

    @Override //registro de usuario
    public void crearUsuario(UsuarioRequest  request) {
        User user = userRepository.findById(request.getIdUser()).orElseThrow(() -> new RuntimeException("User encontrado con ID: " + request.getIdUser()));
        Usuario  usuarioNew= new Usuario();
        System.out.println("Nuevo usuario" + usuarioNew);
        usuarioNew.setNombre(request.getNombre());
        usuarioNew.setApellido(request.getApellido());
        usuarioNew.setSexo(request.getSexo());
        usuarioNew.setDireccion(request.getDireccion());
        usuarioNew.setCelular(request.getCelular());
        usuarioNew.setEstado(request.getEstado());
        usuarioNew.setUser(user);
        usuarioRepository.save(usuarioNew);
    }

    @Override // buscar usuario por id
    public UsuarioResponse obtenerUsuarioPorId(Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        return usuarioMapper.toUsuarioToUsuarioResponse(usuario);
    }

    @Override
    public UsuarioResponse obtenerUsuarioPorIdUser(Long userId) {
        User user = new User();
        user.setId(userId);
        Usuario usuario = usuarioRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado para el User ID: " + userId));
        return usuarioMapper.toUsuarioToUsuarioResponse(usuario);
    }
//    public UsuarioResponse obtenerUsuarioPorIdUser(Long user) {
//        Usuario usuario = usuarioRepository.findByUser(user).orElse( null);
//        return usuarioMapper.toUsuarioToUsuarioResponse(usuario);
//    }


    @Override // obtener todos los usuarios
    public Collection<UsuarioResponse> obtenerTodosLosUsuarios() {
        Collection<Usuario> listUsuarioResponse = usuarioRepository.findAll();
        return usuarioMapper.toListUsuarioToUsuarioResponse(listUsuarioResponse);
    }

    @Override // actualizar usuario
    public void actualizarUsuario(Long id, UsuarioRequest request) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        User user = userRepository.findById(request.getIdUser()).orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + request.getIdUser()));

        if (usuario != null) {
            usuario.setNombre(request.getNombre());
            usuario.setApellido(request.getApellido());
            usuario.setSexo(request.getSexo());
            usuario.setDireccion(request.getDireccion());
            usuario.setCelular(request.getCelular());
            usuario.setEstado(request.getEstado());
            usuario.setUser(user);

            usuarioRepository.save(usuario);
        }
    }

    @Override // eliminar usuario
    public void eliminarUsuario(Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        if (usuario != null) {
            usuarioRepository.delete(usuario);
        }
    }


}