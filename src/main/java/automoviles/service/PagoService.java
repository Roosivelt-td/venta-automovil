package automoviles.service;

import java.util.List;

public interface PagoService {
    PagoDto crearPago(PagoDto pagoDto);
    List<PagoDto> obtenerPagosPorVenta(Long idVenta);
}