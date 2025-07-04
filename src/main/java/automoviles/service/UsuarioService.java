package automoviles.service;

import automoviles.dto.request.UsuarioRequest;
import automoviles.dto.response.UsuarioResponse;

import java.util.Collection;

public interface UsuarioService {

    UsuarioResponse crearUsuario(UsuarioRequest request);
    UsuarioResponse obtenerUsuarioPorId(Long id);
    UsuarioResponse obtenerUsuarioPorIdUser(Long userId);
    Collection<UsuarioResponse> obtenerTodosLosUsuarios();
    void actualizarUsuario(Long id, UsuarioRequest request);
    void eliminarUsuario(Long id);
}