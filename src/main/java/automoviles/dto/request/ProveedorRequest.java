package automoviles.dto.request;

import lombok.Data;

@Data
public class ProveedorRequest {
    private Long id;
    private String nombreEmpresa;
    private String ruc;
    private String contacto;
    private String telefono;
    private String direccion;
}