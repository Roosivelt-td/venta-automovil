package automoviles.dto.response;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class ReembolsoResponse {

    private Long identificador;
    private Long idVenta;  // ID de la venta asociada
    private String motivo;
    private BigDecimal monto;
    private LocalDate fecha;
}