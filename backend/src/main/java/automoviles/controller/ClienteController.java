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

    @PostMapping ("/create")// crear un cliente
    public ResponseEntity<ClienteResponse> crearCliente(@RequestBody ClienteRequest request) {
        System.out.println("=== CONTROLADOR RECIBIÓ REQUEST ===");
        System.out.println("Request completa: " + request);
        System.out.println("Nombre: " + request.getNombre());
        System.out.println("DNI: " + request.getDni());
        System.out.println("=====================================");

        clienteService.crearCliente(request);
        // Buscar el cliente recién creado para devolverlo
        ClienteResponse clienteCreado = clienteService.buscarClientesPorDni(request.getDni()).iterator().next();
        return ResponseEntity.ok(clienteCreado);
    }

    @GetMapping("/{id}") // obtener un cliente por su id
    public ResponseEntity<ClienteResponse> obtenerClientePorId(@PathVariable Long id) {
        return ResponseEntity.ok(clienteService.obtenerClientePorId(id));
    }

    @GetMapping("/todos")// obtener todos los clientes
    public ResponseEntity<Collection<ClienteResponse>> obtenerTodosLosClientes() {
        return ResponseEntity.ok(clienteService.obtenerTodosLosClientes());
    }

    @PutMapping("/update/{id}")// actualizar un cliente por id
    public ResponseEntity<ClienteResponse> actualizarClienteId(@PathVariable Long id, @RequestBody ClienteRequest request) {
        if (clienteService.obtenerClientePorId(id) != null) {
            clienteService.actualizarCliente(id, request);
            return ResponseEntity.ok(clienteService.obtenerClientePorId(id));
        } else {
            throw new RuntimeException("No existe un cliente con el id: " + id);
        }
    }

    @DeleteMapping("/delete/{id}") // eliminar un cliente  por id
    public ResponseEntity<Void> eliminarClienteId(@PathVariable Long id) {
        clienteService.eliminarCliente(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/buscar") // buscar clientes por DNI
    public ResponseEntity<Collection<ClienteResponse>> buscarClientesPorDni(@RequestParam(required = false) Integer dni) {
        if (dni != null) {
            return ResponseEntity.ok(clienteService.buscarClientesPorDni(dni));
        } else {
            return ResponseEntity.ok(clienteService.obtenerTodosLosClientes());
        }
    }


}