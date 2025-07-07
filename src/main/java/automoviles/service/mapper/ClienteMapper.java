package automoviles.service.mapper;

import automoviles.dto.response.ClienteResponse;
import automoviles.dto.response.UsuarioResponse;
import automoviles.model.Cliente;
import automoviles.model.Usuario;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

@Component
public class ClienteMapper {

    public Collection<ClienteResponse> toListClienteToClienteResponse(Collection<Cliente> listarCliente) {
        Collection<ClienteResponse> listarClienteResponse = new ArrayList<ClienteResponse>();
        if (listarCliente != null && !listarCliente.isEmpty()) {
            for (Cliente cliente : listarCliente) {
                ClienteResponse clienteResponse = new ClienteResponse();
                clienteResponse.setIdentificador(cliente.getId());
                clienteResponse.setNombre(cliente.getNombre());
                clienteResponse.setDni(cliente.getDni());
                clienteResponse.setTelefono(cliente.getTelefono());
                clienteResponse.setDireccion(cliente.getDireccion());
                clienteResponse.setCorreo(cliente.getCorreo());
                listarClienteResponse.add(clienteResponse);
            }
        }
        return listarClienteResponse;
    }
    public ClienteResponse toClienteToClienteResponse(Cliente cliente) {
        ClienteResponse clienteResponse = new ClienteResponse();
        if (cliente != null) {
            clienteResponse.setNombre(cliente.getNombre());
            clienteResponse.setDni(cliente.getDni());
            clienteResponse.setTelefono(cliente.getTelefono());
            clienteResponse.setDireccion(cliente.getDireccion());
            clienteResponse.setCorreo(cliente.getCorreo());
        }
        return clienteResponse;
    }
}