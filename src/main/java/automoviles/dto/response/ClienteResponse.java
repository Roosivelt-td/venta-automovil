package automoviles.dto.response;

import lombok.Data;

@Data
public class ClienteResponse {

    private Long identificador;
    private String nombre;
    private Integer dni;
    private String telefono;
    private String direccion;
    private String correo;
}