package automoviles.service.mapper;

import automoviles.dto.VentaDto;
import automoviles.model.Venta;
import org.springframework.stereotype.Component;

@Component
public class VentaMapper {

    public VentaDto toDto(Venta venta) {
        VentaDto dto = new VentaDto();
        dto.setId(venta.getId());
        dto.setIdCliente(venta.getCliente().getId());
        dto.setIdAuto(venta.getAuto().getId());
        dto.setIdUsuario(venta.getUsuario().getId());
        dto.setFecha(venta.getFecha());
        dto.setPrecioVenta(venta.getPrecioVenta());
        return dto;
    }

    public Venta toEntity(VentaDto dto) {
        Venta venta = new Venta();
        // Nota: Aquí solo asignamos IDs. Las entidades Cliente/Auto/Usuario se cargan en el servicio.
        venta.setFecha(dto.getFecha());
        venta.setPrecioVenta(dto.getPrecioVenta());
        return venta;
    }
}