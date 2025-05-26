package automoviles.dto.request;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class CompraRequest {
    private Long id;
    private Long idProveedor;  // ID del proveedor
    private Long idAuto;      // ID del auto
    private LocalDate fecha;
    private BigDecimal precioCompra;
}