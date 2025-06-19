package automoviles.controller;

import automoviles.dto.request.PagoRequest;
import automoviles.dto.response.PagoResponse;
import automoviles.service.PagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/pagos")
public class PagoController {

    @Autowired
    private PagoService pagoService;

    @PostMapping ("/create")// crear un pago
    public void crearPago(@RequestBody PagoRequest request) { pagoService.crearPago(request);
    }

    @GetMapping("/{id}") // obtener un pago por su id
    public ResponseEntity<PagoResponse> obtenerPagoPorId(@PathVariable Long id) {
        return ResponseEntity.ok(pagoService.obtenerPagoPorId(id));
    }

    @GetMapping("/todos")// obtener todos los pagos
    public ResponseEntity<Collection<PagoResponse>> obtenerTodosLosPagos() {
        return ResponseEntity.ok(pagoService.obtenerTodosLosPagos());
    }

    @PutMapping("/update/{id}")// actualizar un pago por id
    public void actualizarPagoId(@PathVariable Long id, @RequestBody PagoRequest request) {
        if (pagoService.obtenerPagoPorId(id) != null) {
            pagoService.actualizarPago(id, request);
        } else {
            throw new RuntimeException("No existe un pago con el id: " + id);
        }
    }

    @DeleteMapping("/delete/{id}") // eliminar un pago  por id
    public void eliminarPagoId(@PathVariable Long id) {
        pagoService.eliminarPago(id);
    }

}