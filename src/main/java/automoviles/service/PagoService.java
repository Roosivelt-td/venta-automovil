package automoviles.service;

import automoviles.dto.request.PagoRequest;
import automoviles.dto.request.UsuarioRequest;
import automoviles.dto.response.PagoResponse;
import automoviles.dto.response.UsuarioResponse;

import java.util.Collection;

public interface PagoService {

    Collection<PagoResponse> obtenerTodosLosPagos();
    PagoResponse obtenerPagoPorId(Long id);
    void crearPago(PagoRequest request);
    void actualizarPago(Long id, PagoRequest request);
    void eliminarPago(Long id);
}