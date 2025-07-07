package automoviles.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Setter
@Getter
public class ReembolsoRequest {
    private Long id;
    private Long idVenta;  // ID de la venta asociada
    private String motivo;
    private BigDecimal monto;
    private LocalDate fecha;
}