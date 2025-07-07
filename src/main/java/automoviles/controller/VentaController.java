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
import java.math.BigDecimal;
import java.time.LocalDate;
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

    // Endpoints de búsqueda
    @GetMapping("/buscar/cliente/{nombreCliente}")
    public ResponseEntity<Collection<VentaResponse>> buscarVentasPorCliente(@PathVariable String nombreCliente) {
        try {
            return ResponseEntity.ok(ventaService.buscarVentasPorCliente(nombreCliente));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/buscar/auto")
    public ResponseEntity<Collection<VentaResponse>> buscarVentasPorAuto(
            @RequestParam String marca,
            @RequestParam String modelo) {
        try {
            return ResponseEntity.ok(ventaService.buscarVentasPorAuto(marca, modelo));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/buscar/usuario/{nombreUsuario}")
    public ResponseEntity<Collection<VentaResponse>> buscarVentasPorUsuario(@PathVariable String nombreUsuario) {
        try {
            return ResponseEntity.ok(ventaService.buscarVentasPorUsuario(nombreUsuario));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/buscar/fecha")
    public ResponseEntity<Collection<VentaResponse>> buscarVentasPorFecha(
            @RequestParam String fechaInicio,
            @RequestParam String fechaFin) {
        try {
            LocalDate inicio = LocalDate.parse(fechaInicio);
            LocalDate fin = LocalDate.parse(fechaFin);
            return ResponseEntity.ok(ventaService.buscarVentasPorFecha(inicio, fin));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/buscar/precio")
    public ResponseEntity<Collection<VentaResponse>> buscarVentasPorPrecio(
            @RequestParam BigDecimal precioMin,
            @RequestParam BigDecimal precioMax) {
        try {
            return ResponseEntity.ok(ventaService.buscarVentasPorPrecio(precioMin, precioMax));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/buscar/termino/{termino}")
    public ResponseEntity<Collection<VentaResponse>> buscarVentasPorTermino(@PathVariable String termino) {
        try {
            return ResponseEntity.ok(ventaService.buscarVentasPorTermino(termino));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}