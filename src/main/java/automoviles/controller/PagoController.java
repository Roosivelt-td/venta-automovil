package automoviles.controller;

import automoviles.dto.PagoDto;
import automoviles.service.PagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/pagos")
public class PagoController {

    @Autowired
    private PagoService pagoService;

    @PostMapping
    public ResponseEntity<PagoDto> crearPago(@RequestBody PagoDto pagoDto) {
        PagoDto nuevoPago = pagoService.crearPago(pagoDto);
        return ResponseEntity.ok(nuevoPago);
    }

    @GetMapping("/venta/{idVenta}")
    public ResponseEntity<List<PagoDto>> obtenerPagosPorVenta(@PathVariable Long idVenta) {
        List<PagoDto> pagos = pagoService.obtenerPagosPorVenta(idVenta);
        return ResponseEntity.ok(pagos);
    }
}