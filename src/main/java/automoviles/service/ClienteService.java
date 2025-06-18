package automoviles.service;

import automoviles.dto.request.ClienteRequest;
import automoviles.dto.response.ClienteResponse;

import java.util.Collection;

public interface ClienteService {

    Collection<ClienteResponse> obtenerTodosLosClientes();
    ClienteResponse obtenerClientePorId(Long id);
    void crearCliente(ClienteRequest request);
    void actualizarCliente(Long id, ClienteRequest request);
    void eliminarCliente(Long id);
}