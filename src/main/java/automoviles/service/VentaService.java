package automoviles.service;

import automoviles.dto.VentaDto;
import java.util.List;

public interface VentaService {
    VentaDto crearVenta(VentaDto ventaDto);
    VentaDto obtenerVentaPorId(Long id);
    List<VentaDto> obtenerTodasLasVentas();
}