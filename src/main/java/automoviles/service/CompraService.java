package automoviles.service;

import java.util.List;

public interface CompraService {
    CompraDto crearCompra(CompraDto compraDto);
    List<CompraDto> obtenerComprasPorProveedor(Long idProveedor);
    List<CompraDto> obtenerComprasPorAuto(Long idAuto);
}