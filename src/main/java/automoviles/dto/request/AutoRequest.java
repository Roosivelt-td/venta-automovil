package automoviles.dto.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class AutoRequest {
    private Long id;
    private String marca;
    private String modelo;
    private Integer anio;
    private String color;
    private Integer kilometraje;
    private String tipo;
    private BigDecimal precio;
    private String descripcion;
    private String imagenUrl;
    private String estado;
}