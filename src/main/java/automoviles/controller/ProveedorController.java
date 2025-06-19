package automoviles.controller;

import automoviles.dto.request.ProveedorRequest;
import automoviles.dto.response.ProveedorResponse;
import automoviles.service.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/proveedores")
public class ProveedorController {

    @Autowired
    private ProveedorService proveedorService;

    @PostMapping ("/create")// crear un proveedor
    public void crearProveedor(@RequestBody ProveedorRequest request) { proveedorService.crearProveedor(request);
    }

    @GetMapping("/{id}") // obtener un proveedor por su id
    public ResponseEntity<ProveedorResponse> obtenerProveedorPorId(@PathVariable Long id) {
        return ResponseEntity.ok(proveedorService.obtenerProveedorPorId(id));
    }

    @GetMapping("/todos")// obtener todos los proveedors
    public ResponseEntity<Collection<ProveedorResponse>> obtenerTodosLosProveedors() {
        return ResponseEntity.ok(proveedorService.obtenerTodosLosProveedors());
    }

    @PutMapping("/update/{id}")// actualizar un proveedor por id
    public void actualizarProveedorId(@PathVariable Long id, @RequestBody ProveedorRequest request) {
        if (proveedorService.obtenerProveedorPorId(id) != null) {
            proveedorService.actualizarProveedor(id, request);
        } else {
            throw new RuntimeException("No existe un proveedor con el id: " + id);
        }
    }

    @DeleteMapping("/delete/{id}") // eliminar un proveedor  por id
    public void eliminarProveedorId(@PathVariable Long id) {
        proveedorService.eliminarProveedor(id);
    }


    @GetMapping("/buscar")
    public ResponseEntity<?> buscarProveedores(
            @RequestParam(required = false) String nombreEmpresa,
            @RequestParam(required = false) String ruc) {
        try {
            Collection<ProveedorResponse> proveedores = proveedorService.buscarPorNombreEmpresaORuc(
                    nombreEmpresa != null ? nombreEmpresa : "",
                    ruc != null ? ruc : ""
            );
            return ResponseEntity.ok(proveedores);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }
}