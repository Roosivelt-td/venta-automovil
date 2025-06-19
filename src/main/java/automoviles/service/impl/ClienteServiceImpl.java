package automoviles.service.impl;

import automoviles.dto.request.ClienteRequest;
import automoviles.dto.response.ClienteResponse;
import automoviles.model.Cliente;
import automoviles.repository.ClienteRepository;
import automoviles.service.ClienteService;
import automoviles.service.mapper.ClienteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ClienteMapper clienteMapper;

    @Override //registro de cliente
    public void crearCliente(ClienteRequest request) {
        Cliente clienteNew = new Cliente();
        System.out.println("Nuevo Cliente" + clienteNew);
        clienteNew.setNombre(request.getNombre());
        clienteNew.setDni(request.getDni());
        clienteNew.setTelefono(request.getTelefono());
        clienteNew.setDireccion(request.getDireccion());
        clienteNew.setCorreo(request.getCorreo());
        clienteRepository.save(clienteNew);

    }

    @Override // buscar Cliente por id
    public ClienteResponse obtenerClientePorId(Long id) {
        Cliente cliente = clienteRepository.findById(id).orElse(null);
        return clienteMapper.toClienteToClienteResponse(cliente);
    }
    // buscar Cliente por dni local
    public ClienteResponse obtenerClientePorDNI(Long dni) {
        Cliente cliente = clienteRepository.findById(dni).orElse(null);
        return clienteMapper.toClienteToClienteResponse(cliente);
    }

     // buscar Cliente por dni reniec
    public ClienteResponse obtenerClientePorDniReniec(Long dni) {
        Cliente cliente = clienteRepository.findById(dni).orElse(null);
        return clienteMapper.toClienteToClienteResponse(cliente);
    }


    @Override // obtener todos los clientes
    public Collection<ClienteResponse> obtenerTodosLosClientes() {
        Collection<Cliente> listClienteResponse = clienteRepository.findAll();
        return clienteMapper.toListClienteToClienteResponse(listClienteResponse);
    }

    @Override // actualizar clientes
    public void actualizarCliente(Long id, ClienteRequest request) {
        Cliente cliente = clienteRepository.findById(id).orElse(null);
        if (cliente != null) {
            cliente.setNombre(request.getNombre());
            cliente.setDni(request.getDni());
            cliente.setTelefono(request.getTelefono());
            cliente.setDireccion(request.getDireccion());
            cliente.setCorreo(request.getCorreo());
            clienteRepository.save(cliente);
        }
    }

    @Override // eliminar clientes
    public void eliminarCliente(Long id) {
        Cliente cliente = clienteRepository.findById(id).orElse(null);
        if (cliente != null) {
            clienteRepository.delete(cliente);
        }
    }
}