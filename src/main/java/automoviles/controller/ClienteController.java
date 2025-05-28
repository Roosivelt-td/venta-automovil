package automoviles.controller;

import automoviles.dto.request.ClienteRequest;
import automoviles.dto.response.ClienteResponse;
import automoviles.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @PostMapping // crear un cliente
    public void crearCliente(@RequestBody ClienteRequest request) { clienteService.crearCliente(request);
    }

    @GetMapping("/{id}") // obtener un cliente por su id
    public ResponseEntity<ClienteResponse> obtenerClientePorId(@PathVariable Long id) {
        return ResponseEntity.ok(clienteService.obtenerClientePorId(id));
    }

    @GetMapping// obtener todos los clientes
    public ResponseEntity<Collection<ClienteResponse>> obtenerTodosLosClientes() {
        return ResponseEntity.ok(clienteService.obtenerTodosLosClientes());
    }

    @PutMapping("/{id}/update")// actualizar un cliente por id
    public void actualizarClienteId(@PathVariable Long id, @RequestBody ClienteRequest request) {
        if (clienteService.obtenerClientePorId(id) != null) {
            clienteService.actualizarCliente(id, request);
        } else {
            throw new RuntimeException("No existe un cliente con el id: " + id);
        }
    }

    @DeleteMapping("/{id}/delete") // eliminar un cliente  por id
    public void eliminarClienteId(@PathVariable Long id) {
        clienteService.eliminarCliente(id);
    }

}