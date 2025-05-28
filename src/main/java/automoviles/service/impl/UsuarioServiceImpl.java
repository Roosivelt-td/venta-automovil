package automoviles.service.impl;

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

    @Autowired
    private UsuarioMapper usuarioMapper;

    @Override //registro de usuario
    public void crearUsuario(UsuarioRequest  request) {
        Usuario  usuarioNew= new Usuario();
        System.out.println("Nuevo usuario" + usuarioNew);
        usuarioNew.setNombre(request.getNombre());
        usuarioNew.setApellido(request.getApellido());
        usuarioNew.setCorreo(request.getCorreo());
        usuarioNew.setContrasena(request.getContrasena());
        usuarioNew.setRol(request.getRol());
        usuarioNew.setEstado(request.getEstado());
        usuarioRepository.save(usuarioNew);
    }

    @Override // buscar usuario por id
    public UsuarioResponse obtenerUsuarioPorId(Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        return usuarioMapper.toUsuarioToUsuarioResponse(usuario);
    }

    @Override // obtener todos los usuarios
    public Collection<UsuarioResponse> obtenerTodosLosUsuarios() {
        Collection<Usuario> listUsuarioResponse = usuarioRepository.findAll();
        return usuarioMapper.toListUsuarioToUsuarioResponse(listUsuarioResponse);
    }

    @Override // actualizar usuario
    public void actualizarUsuario(Long id, UsuarioRequest request) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        if (usuario != null) {
            usuario.setNombre(request.getNombre());
            usuario.setApellido(request.getApellido());
            usuario.setCorreo(request.getCorreo());
            usuario.setContrasena(request.getContrasena());
            usuario.setRol(request.getRol());
            usuario.setEstado(request.getEstado());
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