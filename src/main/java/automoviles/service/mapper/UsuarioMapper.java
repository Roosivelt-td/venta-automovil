package automoviles.service.mapper;

import automoviles.dto.response.UsuarioResponse;
import automoviles.model.Usuario;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

@Component
public class UsuarioMapper {

    public Collection<UsuarioResponse> toListUsuarioToUsuarioResponse(Collection<Usuario> listarUsuario) {
        Collection<UsuarioResponse> listarUsuarioResponse = new ArrayList<UsuarioResponse>();

        if (listarUsuario != null && !listarUsuario.isEmpty()) {
            for (Usuario usuario : listarUsuario) {
                UsuarioResponse usuarioResponse = new UsuarioResponse();
                usuarioResponse.setIdentificador(usuario.getId());
                usuarioResponse.setNombre(usuario.getNombre());
                usuarioResponse.setApellido(usuario.getApellido());
                usuarioResponse.setSexo(usuario.getSexo());
                usuarioResponse.setDireccion(usuario.getDireccion());
                usuarioResponse.setCelular(usuario.getCelular());
                usuarioResponse.setEstado(usuario.getEstado());
                listarUsuarioResponse.add(usuarioResponse);
                if (usuario.getUser() != null) {
                    usuarioResponse.setIdUser(usuario.getUser().getId());
                } else {
                    usuarioResponse.setIdUser(null);
                }
                listarUsuarioResponse.add(usuarioResponse);
            }
        }
        return listarUsuarioResponse;
    }

    public UsuarioResponse toUsuarioToUsuarioResponse(Usuario usuario) {
        UsuarioResponse usuarioResponse = new UsuarioResponse();
        if (usuario != null) {
            usuarioResponse.setNombre(usuario.getNombre());
            usuarioResponse.setApellido(usuario.getApellido());
            usuarioResponse.setSexo(usuario.getSexo());
            usuarioResponse.setDireccion(usuario.getDireccion());
            usuarioResponse.setCelular(usuario.getCelular());
            usuarioResponse.setEstado(usuario.getEstado());
            if (usuario.getUser() != null) {
                usuarioResponse.setIdUser(usuario.getUser().getId());
            } else {
                usuarioResponse.setIdUser(null);
            }
        }
        return usuarioResponse;
    }
}