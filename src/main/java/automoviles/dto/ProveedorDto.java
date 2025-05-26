package automoviles.dto;

import lombok.Data;

@Data
public class ProveedorDto {
    private Long id;
    private String nombreEmpresa;
    private String ruc;
    private String contacto;
    private String telefono;
    private String direccion;
}