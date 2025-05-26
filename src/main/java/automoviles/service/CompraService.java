package automoviles.service;

import automoviles.dto.CompraDto;
import java.util.List;

public interface CompraService {
    CompraDto crearCompra(CompraDto compraDto);
    List<CompraDto> obtenerComprasPorProveedor(Long idProveedor);
    List<CompraDto> obtenerComprasPorAuto(Long idAuto);
}