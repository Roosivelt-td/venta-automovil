package automoviles.service.mapper;

import automoviles.model.Compra;
import org.springframework.stereotype.Component;

@Component
public class CompraMapper {

    public CompraDto toDto(Compra compra) {
        CompraDto dto = new CompraDto();
        dto.setId(compra.getId());
        dto.setIdProveedor(compra.getProveedor().getId());
        dto.setIdAuto(compra.getAuto().getId());
        dto.setFecha(compra.getFecha());
        dto.setPrecioCompra(compra.getPrecioCompra());
        return dto;
    }

    public Compra toEntity(CompraDto dto) {
        Compra compra = new Compra();
        compra.setFecha(dto.getFecha());
        compra.setPrecioCompra(dto.getPrecioCompra());
        return compra;
    }
}