package automoviles.dto.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ClienteRequest {
    private Long id;
    private String nombre;
    private Integer dni;
    private String telefono;
    private String direccion;
    private String correo;
}