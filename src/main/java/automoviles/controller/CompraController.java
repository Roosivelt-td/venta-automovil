package automoviles.controller;

import automoviles.dto.request.CompraRequest;
import automoviles.dto.response.CompraResponse;
import automoviles.service.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/compras")
public class CompraController {

    @Autowired
    private CompraService compraService;

    @PostMapping ("/create")// crear un compra
    public void crearCompra(@RequestBody CompraRequest request) { compraService.crearCompra(request);
    }

    @GetMapping("/{id}") // obtener un compra por su id
    public ResponseEntity<CompraResponse> obtenerCompraPorId(@PathVariable Long id) {
        return ResponseEntity.ok(compraService.obtenerCompraPorId(id));
    }

    @GetMapping("/todos")// obtener todos los compras
    public ResponseEntity<Collection<CompraResponse>> obtenerTodosLosCompras() {
        return ResponseEntity.ok(compraService.obtenerTodosLosCompras());
    }

    @PutMapping("/update/{id}")// actualizar un compra por id
    public void actualizarCompraId(@PathVariable Long id, @RequestBody CompraRequest request) {
        if (compraService.obtenerCompraPorId(id) != null) {
            compraService.actualizarCompra(id, request);
        } else {
            throw new RuntimeException("No existe un compra con el id: " + id);
        }
    }

    @DeleteMapping("/delete/{id}") // eliminar un compra  por id
    public void eliminarCompraId(@PathVariable Long id) {
        compraService.eliminarCompra(id);
    }

}