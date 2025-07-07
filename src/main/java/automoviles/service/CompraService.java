package automoviles.service;

import automoviles.dto.request.CompraRequest;
import automoviles.dto.response.CompraResponse;

import java.util.Collection;

public interface CompraService {

    Collection<CompraResponse> obtenerTodosLosCompras();
    CompraResponse obtenerCompraPorId(Long id);
    void crearCompra(CompraRequest request);
    void actualizarCompra(Long id, CompraRequest request);
    void eliminarCompra(Long id);
}