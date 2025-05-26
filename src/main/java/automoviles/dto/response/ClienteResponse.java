package automoviles.dto.response;

import lombok.Data;

@Data
public class ClienteResponse {
    private Long id;
    private String nombre;
    private String dni;
    private String telefono;
    private String direccion;
    private String correo;
}