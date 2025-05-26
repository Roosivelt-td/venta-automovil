package automoviles.dto.request;

import lombok.Data;

@Data
public class ClienteRequest {
    private Long id;
    private String nombre;
    private String dni;
    private String telefono;
    private String direccion;
    private String correo;
}