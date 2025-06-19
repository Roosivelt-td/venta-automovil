package automoviles.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Setter
@Getter
public class VentaRequest {
    private Long id;
    private Long idCliente;  // Solo almacena el ID para simplificar
    private Long idAuto;
    private Long idUsuario;
    private LocalDate fecha;
    private BigDecimal precioVenta;
}