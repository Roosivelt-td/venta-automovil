package automoviles.dto.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProveedorRequest {
    private Long id;
    private String nombreEmpresa;
    private String ruc;
    private String contacto;
    private String telefono;
    private String direccion;
}