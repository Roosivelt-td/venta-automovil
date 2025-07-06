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
        System.out.println("=== DATOS RECIBIDOS DEL FRONTEND ===");
        System.out.println("Nombre: " + request.getNombre());
        System.out.println("DNI: " + request.getDni());
        System.out.println("Teléfono: " + request.getTelefono());
        System.out.println("Dirección: " + request.getDireccion());
        System.out.println("Correo: " + request.getCorreo());
        System.out.println("=====================================");

        Cliente clienteNew = new Cliente();
        clienteNew.setNombre(request.getNombre());
        clienteNew.setDni(request.getDni());
        clienteNew.setTelefono(request.getTelefono());
        clienteNew.setDireccion(request.getDireccion());
        clienteNew.setCorreo(request.getCorreo());

        System.out.println("Cliente a guardar: " + clienteNew);
        Cliente clienteGuardado = clienteRepository.save(clienteNew);
        System.out.println("Cliente guardado exitosamente con ID: " + clienteGuardado.getId());
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

    @Override // buscar clientes por DNI
    public Collection<ClienteResponse> buscarClientesPorDni(Integer dni) {
        Collection<Cliente> clientes = clienteRepository.findByDni(dni);
        return clienteMapper.toListClienteToClienteResponse(clientes);
    }
}