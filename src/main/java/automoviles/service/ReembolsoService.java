package automoviles.service;

import automoviles.dto.ReembolsoDto;
import java.util.List;

public interface ReembolsoService {
    ReembolsoDto crearReembolso(ReembolsoDto reembolsoDto);
    List<ReembolsoDto> obtenerReembolsosPorVenta(Long idVenta);
}