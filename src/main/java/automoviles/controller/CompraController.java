package automoviles.controller;

import automoviles.service.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/compras")
public class CompraController {

    @Autowired
    private CompraService compraService;

    @PostMapping
    public ResponseEntity<CompraDto> crearCompra(@RequestBody CompraDto compraDto) {
        CompraDto nuevaCompra = compraService.crearCompra(compraDto);
        return ResponseEntity.ok(nuevaCompra);
    }

    @GetMapping("/proveedor/{idProveedor}")
    public ResponseEntity<List<CompraDto>> obtenerComprasPorProveedor(@PathVariable Long idProveedor) {
        List<CompraDto> compras = compraService.obtenerComprasPorProveedor(idProveedor);
        return ResponseEntity.ok(compras);
    }

    @GetMapping("/auto/{idAuto}")
    public ResponseEntity<List<CompraDto>> obtenerComprasPorAuto(@PathVariable Long idAuto) {
        List<CompraDto> compras = compraService.obtenerComprasPorAuto(idAuto);
        return ResponseEntity.ok(compras);
    }
}