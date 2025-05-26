package automoviles.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioRequest {
    private Long id;
    private String nombre;
    private String apellido;
    private String correo;
    private String contrasena;
    private String rol;
    private Boolean estado;

}