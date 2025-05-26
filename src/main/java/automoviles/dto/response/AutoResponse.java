package automoviles.dto.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AutoResponse {

    private Long identificador;
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