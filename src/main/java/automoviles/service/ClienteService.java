package automoviles.service;

import automoviles.dto.ClienteDto;
import java.util.List;

public interface ClienteService {
    ClienteDto crearCliente(ClienteDto clienteDto);
    ClienteDto obtenerClientePorId(Long id);
    List<ClienteDto> obtenerTodosLosClientes();
    ClienteDto actualizarCliente(Long id, ClienteDto clienteDto);
    void eliminarCliente(Long id);
}