package automoviles.service;

import automoviles.dto.request.ProveedorRequest;
import automoviles.dto.response.ProveedorResponse;

import java.util.Collection;

public interface ProveedorService {

    Collection<ProveedorResponse> obtenerTodosLosProveedors();
    ProveedorResponse obtenerProveedorPorId(Long id);
    void crearProveedor(ProveedorRequest request);
    void actualizarProveedor(Long id, ProveedorRequest request);
    void eliminarProveedor(Long id);

}