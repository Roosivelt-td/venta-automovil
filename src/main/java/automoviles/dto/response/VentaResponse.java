package automoviles.dto.response;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class VentaResponse {

    private Long identificador;
    private LocalDate fecha;
    private BigDecimal precioVenta;
    private String metodoPago;
    private String observaciones;
    private ClienteResponse cliente;
    private AutoResponse auto;
    private UsuarioResponse usuario;
}