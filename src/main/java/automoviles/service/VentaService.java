package automoviles.service;

import automoviles.dto.request.VentaRequest;
import automoviles.dto.response.VentaResponse;
import java.util.Collection;

public interface VentaService {

    Collection<VentaResponse> obtenerTodosLosVentas();
    VentaResponse obtenerVentaPorId(Long id);
    void crearVenta(VentaRequest request);
    void actualizarVenta(Long id, VentaRequest request);
    void eliminarVenta(Long id);
}