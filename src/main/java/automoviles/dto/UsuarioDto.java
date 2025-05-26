package automoviles.dto;

import lombok.Data;

@Data
public class UsuarioDto {
    private Long id;
    private String nombre;
    private String apellido;
    private String correo;
    private String contrasena;
    private String rol;
    private Boolean estado;

}