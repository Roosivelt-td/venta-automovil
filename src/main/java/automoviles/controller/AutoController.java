package automoviles.controller;

import automoviles.dto.AutoDto;
import automoviles.service.AutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/autos")
public class AutoController {

    @Autowired
    private AutoService autoService;
//hola
    @PostMapping
    public ResponseEntity<AutoDto> crearAuto(@RequestBody AutoDto autoDto) {
        AutoDto nuevoAuto = autoService.crearAuto(autoDto);
        return ResponseEntity.ok(nuevoAuto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AutoDto> obtenerAutoPorId(@PathVariable Long id) {
        AutoDto auto = autoService.obtenerAutoPorId(id);
        return ResponseEntity.ok(auto);
    }

    @GetMapping
    public ResponseEntity<List<AutoDto>> obtenerTodosLosAutos() {
        List<AutoDto> autos = autoService.obtenerTodosLosAutos();
        return ResponseEntity.ok(autos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AutoDto> actualizarAuto(@PathVariable Long id, @RequestBody AutoDto autoDto) {
        AutoDto autoActualizado = autoService.actualizarAuto(id, autoDto);
        return ResponseEntity.ok(autoActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarAuto(@PathVariable Long id) {
        autoService.eliminarAuto(id);
        return ResponseEntity.noContent().build();
    }
}