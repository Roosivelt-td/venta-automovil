package automoviles.service;

import automoviles.dto.PagoDto;
import java.util.List;

public interface PagoService {
    PagoDto crearPago(PagoDto pagoDto);
    List<PagoDto> obtenerPagosPorVenta(Long idVenta);
}