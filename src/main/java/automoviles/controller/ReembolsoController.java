package automoviles.controller;

import automoviles.service.ReembolsoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/reembolsos")
public class ReembolsoController {

    @Autowired
    private ReembolsoService reembolsoService;

    @PostMapping
    public ResponseEntity<ReembolsoDto> crearReembolso(@RequestBody ReembolsoDto reembolsoDto) {
        ReembolsoDto nuevoReembolso = reembolsoService.crearReembolso(reembolsoDto);
        return ResponseEntity.ok(nuevoReembolso);
    }

    @GetMapping("/venta/{idVenta}")
    public ResponseEntity<List<ReembolsoDto>> obtenerReembolsosPorVenta(@PathVariable Long idVenta) {
        List<ReembolsoDto> reembolsos = reembolsoService.obtenerReembolsosPorVenta(idVenta);
        return ResponseEntity.ok(reembolsos);
    }
}