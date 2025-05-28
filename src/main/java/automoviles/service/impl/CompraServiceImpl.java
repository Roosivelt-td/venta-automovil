package automoviles.service.impl;

import automoviles.dto.request.CompraRequest;
import automoviles.dto.response.CompraResponse;
import automoviles.model.*;
import automoviles.repository.*;
import automoviles.service.CompraService;
import automoviles.service.mapper.CompraMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class CompraServiceImpl implements CompraService {

    @Autowired
    private CompraRepository compraRepository;

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Autowired
    private AutoRepository autoRepository;

    @Autowired
    private CompraMapper compraMapper;

    @Override
    public Collection<CompraResponse> obtenerTodosLosCompras() {
        Collection<Compra> compras = compraRepository.findAll();
        return compraMapper.toListCompraToCompraResponse(compras);
    }

    @Override
    public CompraResponse obtenerCompraPorId(Long id) {
        Compra compra = compraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Compra no encontrada con ID: " + id));
        return compraMapper.toCompraToCompraResponse(compra);
    }

    @Override
    public void crearCompra(CompraRequest request) {
        Compra compra = new Compra();

        // Obtener proveedor y auto asociados
        Proveedor proveedor = proveedorRepository.findById(request.getIdProveedor())
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado con ID: " + request.getIdProveedor()));

        Auto auto = autoRepository.findById(request.getIdAuto())
                .orElseThrow(() -> new RuntimeException("Auto no encontrado con ID: " + request.getIdAuto()));

        // Configurar la compra
        compra.setProveedor(proveedor);
        compra.setAuto(auto);
        compra.setFecha(request.getFecha());
        compra.setPrecioCompra(request.getPrecioCompra());

        // Guardar la compra
        compraRepository.save(compra);

        // Actualizar el estado del auto a "Disponible" (opcional)
        auto.setEstado("Disponible");
        autoRepository.save(auto);
    }

    @Override
    public void actualizarCompra(Long id, CompraRequest request) {
        Compra compraExistente = compraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Compra no encontrada con ID: " + id));

        // Obtener proveedor y auto asociados
        Proveedor proveedor = proveedorRepository.findById(request.getIdProveedor())
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado con ID: " + request.getIdProveedor()));

        Auto auto = autoRepository.findById(request.getIdAuto())
                .orElseThrow(() -> new RuntimeException("Auto no encontrado con ID: " + request.getIdAuto()));

        // Actualizar la compra
        compraExistente.setProveedor(proveedor);
        compraExistente.setAuto(auto);
        compraExistente.setFecha(request.getFecha());
        compraExistente.setPrecioCompra(request.getPrecioCompra());

        compraRepository.save(compraExistente);
    }

    @Override
    public void eliminarCompra(Long id) {
        if (!compraRepository.existsById(id)) {
            throw new RuntimeException("Compra no encontrada con ID: " + id);
        }
        compraRepository.deleteById(id);
    }
}