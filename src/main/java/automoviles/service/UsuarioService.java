package automoviles.service;

import automoviles.dto.request.UsuarioRequest;
import automoviles.dto.response.UsuarioResponse;

import java.util.Collection;

public interface UsuarioService {

    Collection<UsuarioResponse> obtenerTodosLosUsuarios();
    UsuarioResponse obtenerUsuarioPorId(Long id);
    void crearUsuario(UsuarioRequest request);
    void actualizarUsuario(Long id, UsuarioRequest request);
    void eliminarUsuario(Long id);

    UsuarioResponse obtenerUsuarioPorIdUser(Long user);
}