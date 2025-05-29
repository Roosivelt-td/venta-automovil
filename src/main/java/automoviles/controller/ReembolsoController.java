package automoviles.controller;

import automoviles.dto.request.ReembolsoRequest;
import automoviles.dto.response.ReembolsoResponse;
import automoviles.service.ReembolsoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/reembolsos")
public class ReembolsoController {

    @Autowired
    private ReembolsoService reembolsoService;

    @PostMapping ("/create")// crear un reembolso
    public void crearReembolso(@RequestBody ReembolsoRequest request) { reembolsoService.crearReembolso(request);
    }

    @GetMapping("/{id}") // obtener un reembolso por su id
    public ResponseEntity<ReembolsoResponse> obtenerReembolsoPorId(@PathVariable Long id) {
        return ResponseEntity.ok(reembolsoService.obtenerReembolsoPorId(id));
    }

    @GetMapping("/todos")// obtener todos los reembolsos
    public ResponseEntity<Collection<ReembolsoResponse>> obtenerTodosLosReembolsos() {
        return ResponseEntity.ok(reembolsoService.obtenerTodosLosReembolsos());
    }

    @PutMapping("/update/{id}")// actualizar un reembolso por id
    public void actualizarReembolsoId(@PathVariable Long id, @RequestBody ReembolsoRequest request) {
        if (reembolsoService.obtenerReembolsoPorId(id) != null) {
            reembolsoService.actualizarReembolso(id, request);
        } else {
            throw new RuntimeException("No existe un reembolso con el id: " + id);
        }
    }

    @DeleteMapping("/delete/{id}") // eliminar un reembolso  por id
    public void eliminarReembolsoId(@PathVariable Long id) {
        reembolsoService.eliminarReembolso(id);
    }

}