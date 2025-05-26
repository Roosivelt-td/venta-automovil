package automoviles.controller;

import automoviles.dto.VentaDto;
import automoviles.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/ventas")
public class VentaController {

    @Autowired
    private VentaService ventaService;

    @PostMapping
    public ResponseEntity<VentaDto> crearVenta(@RequestBody VentaDto ventaDto) {
        VentaDto nuevaVenta = ventaService.crearVenta(ventaDto);
        return ResponseEntity.ok(nuevaVenta);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VentaDto> obtenerVentaPorId(@PathVariable Long id) {
        VentaDto venta = ventaService.obtenerVentaPorId(id);
        return ResponseEntity.ok(venta);
    }

    @GetMapping
    public ResponseEntity<List<VentaDto>> obtenerTodasLasVentas() {
        List<VentaDto> ventas = ventaService.obtenerTodasLasVentas();
        return ResponseEntity.ok(ventas);
    }
}