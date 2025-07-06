package automoviles.service;

import automoviles.dto.request.ReembolsoRequest;
import automoviles.dto.response.ReembolsoResponse;
import java.util.Collection;

public interface ReembolsoService {

    Collection<ReembolsoResponse> obtenerTodosLosReembolsos();
    ReembolsoResponse obtenerReembolsoPorId(Long id);
    void crearReembolso(ReembolsoRequest request);
    void actualizarReembolso(Long id, ReembolsoRequest request);
    void eliminarReembolso(Long id);
}