package automoviles.dto.request;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class ReembolsoRequest {
    private Long id;
    private Long idVenta;  // ID de la venta asociada
    private String motivo;
    private BigDecimal monto;
    private LocalDate fecha;
}