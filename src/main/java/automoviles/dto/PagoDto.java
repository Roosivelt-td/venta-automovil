package automoviles.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class PagoDto {
    private Long id;
    private Long idVenta; // ID de la venta asociada
    private String metodoPago;
    private BigDecimal monto;
    private LocalDate fecha;
}