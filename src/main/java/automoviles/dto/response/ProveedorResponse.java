package automoviles.dto.response;

import lombok.Data;

@Data
public class ProveedorResponse {

    private Long identificador;
    private String nombreEmpresa;
    private String ruc;
    private String contacto;
    private String telefono;
    private String direccion;
}