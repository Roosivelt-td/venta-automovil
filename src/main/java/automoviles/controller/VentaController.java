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
