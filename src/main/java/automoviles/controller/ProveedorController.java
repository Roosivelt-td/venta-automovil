package automoviles.controller;

import automoviles.dto.ProveedorDto;
import automoviles.service.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/proveedores")
public class ProveedorController {

    @Autowired
    private ProveedorService proveedorService;

    @PostMapping
    public ResponseEntity<ProveedorDto> crearProveedor(@RequestBody ProveedorDto proveedorDto) {
        ProveedorDto nuevoProveedor = proveedorService.crearProveedor(proveedorDto);
        return ResponseEntity.ok(nuevoProveedor);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProveedorDto> obtenerProveedorPorId(@PathVariable Long id) {
        ProveedorDto proveedor = proveedorService.obtenerProveedorPorId(id);
        return ResponseEntity.ok(proveedor);
    }

    @GetMapping
    public ResponseEntity<List<ProveedorDto>> obtenerTodosLosProveedores() {
        List<ProveedorDto> proveedores = proveedorService.obtenerTodosLosProveedores();
        return ResponseEntity.ok(proveedores);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProveedorDto> actualizarProveedor(@PathVariable Long id, @RequestBody ProveedorDto proveedorDto) {
        ProveedorDto proveedorActualizado = proveedorService.actualizarProveedor(id, proveedorDto);
        return ResponseEntity.ok(proveedorActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProveedor(@PathVariable Long id) {
        proveedorService.eliminarProveedor(id);
        return ResponseEntity.noContent().build();
    }
}