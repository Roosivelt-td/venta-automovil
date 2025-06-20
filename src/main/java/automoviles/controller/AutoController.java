package automoviles.controller;

import automoviles.dto.request.AutoRequest;
import automoviles.dto.response.AutoResponse;
import automoviles.service.AutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/autos")
public class AutoController {

    @Autowired
    private AutoService autoService;

    @PostMapping ("/create")// crear auto
    public void crearAuto(@RequestBody AutoRequest request) { autoService.crearAuto(request);}

    @GetMapping("/{id}") // obtener un auto por su id
    public ResponseEntity<AutoResponse> obtenerAutoPorId(@PathVariable Long id) {
        return ResponseEntity.ok(autoService.obtenerAutoPorId(id));
    }
    @GetMapping ("/")// obtener todos los autos de BD
    public ResponseEntity<Collection<AutoResponse>> obtenerTodosLosAutos() {
        return ResponseEntity.ok(autoService.obtenerTodosLosAutos());
    }
    @PutMapping("/update/{id}")// actualizar un auto por id
    public void actualizarAutoId(@PathVariable Long id, @RequestBody AutoRequest request) {
        if (autoService.obtenerAutoPorId(id) != null) {
            autoService.actualizarAuto(id, request);
        } else {
            throw new RuntimeException("No existe un auto con el id: " + id);
        }
    }
    @DeleteMapping("/delete/{id}") // eliminar un auto  por id
    public void eliminarAutoId(@PathVariable Long id) {
        autoService.eliminarAuto(id);
    }

    @GetMapping("/marca/{marca}")
    public ResponseEntity<Collection<AutoResponse>> obtenerAutosPorMarca(@PathVariable String marca) {
        return ResponseEntity.ok(autoService.obtenerAutosPorMarca(marca));
    }

    @GetMapping("/buscar")
    public ResponseEntity<?> obtenerAutosPorMarcaYModelo(
            @RequestParam String marca,
            @RequestParam String modelo) {
        try {
            Collection<AutoResponse> autos = autoService.obtenerAutosPorMarcaYModelo(marca, modelo);
            return ResponseEntity.ok(autos);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

}