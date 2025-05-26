package automoviles.dto.response;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class VentaResponse {
    private Long id;
    private Long idCliente;  // Solo almacena el ID para simplificar
    private Long idAuto;
    private Long idUsuario;
    private LocalDate fecha;
    private BigDecimal precioVenta;
}