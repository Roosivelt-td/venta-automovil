package automoviles.dto.response;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class CompraResponse {

    private Long identificador;
    private Long idProveedor;  // ID del proveedor
    private Long idAuto;      // ID del auto
    private LocalDate fecha;
    private BigDecimal precioCompra;
}