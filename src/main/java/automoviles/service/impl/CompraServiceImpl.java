package automoviles.service.impl;

import automoviles.model.*;
import automoviles.repository.*;
import automoviles.service.CompraService;
import automoviles.service.mapper.CompraMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

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
    public CompraDto crearCompra(CompraDto compraDto) {
        Compra compra = compraMapper.toEntity(compraDto);
        Proveedor proveedor = proveedorRepository.findById(compraDto.getIdProveedor())
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado"));
        Auto auto = autoRepository.findById(compraDto.getIdAuto())
                .orElseThrow(() -> new RuntimeException("Auto no encontrado"));

        compra.setProveedor(proveedor);
        compra.setAuto(auto);
        compra = compraRepository.save(compra);

        // Actualizar el estado del auto a "Disponible" (opcional)
        auto.setEstado("Disponible");
        autoRepository.save(auto);

        return compraMapper.toDto(compra);
    }

    @Override
    public List<CompraDto> obtenerComprasPorProveedor(Long idProveedor) {
        List<Compra> compras = compraRepository.findByProveedorId(idProveedor);
        return compras.stream()
                .map(compraMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CompraDto> obtenerComprasPorAuto(Long idAuto) {
        List<Compra> compras = compraRepository.findByAutoId(idAuto);
        return compras.stream()
                .map(compraMapper::toDto)
                .collect(Collectors.toList());
    }
}