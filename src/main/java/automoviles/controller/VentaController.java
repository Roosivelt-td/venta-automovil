package automoviles.controller;

import automoviles.dto.request.UsuarioRequest;
import automoviles.dto.request.VentaRequest;
import automoviles.dto.response.UsuarioResponse;
import automoviles.dto.response.VentaResponse;
import automoviles.service.UsuarioService;
import automoviles.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/ventas")
public class VentaController {

    @Autowired
    private VentaService ventaService;

    @PostMapping("/create") // crear una venta de auto
    public void crearVenta(@RequestBody VentaRequest request) { ventaService.crearVenta(request);
    }

    @GetMapping("/{id}") // obtener un venta por su id
    public ResponseEntity<VentaResponse> obtenerVentaPorId(@PathVariable Long id) {
        return ResponseEntity.ok(ventaService.obtenerVentaPorId(id));
    }

    @GetMapping("/todos")// obtener todos los ventas
    public ResponseEntity<Collection<VentaResponse>> obtenerTodosLosVentas() {
        return ResponseEntity.ok(ventaService.obtenerTodosLosVentas());
    }

    @PutMapping("/update/{id}")// actualizar un venta por id
    public void actualizarVentaId(@PathVariable Long id, @RequestBody VentaRequest request) {
        if (ventaService.obtenerVentaPorId(id) != null) {
            ventaService.actualizarVenta(id, request);
        } else {
            throw new RuntimeException("No existe un venta con el id: " + id);
        }
    }

    @DeleteMapping("/delete/{id}") // eliminar un venta  por id
    public void eliminarVentaId(@PathVariable Long id) {
        ventaService.eliminarVenta(id);
    }

}