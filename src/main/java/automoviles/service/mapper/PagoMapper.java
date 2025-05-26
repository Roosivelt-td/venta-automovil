package automoviles.service.mapper;

import automoviles.dto.PagoDto;
import automoviles.model.Pago;
import org.springframework.stereotype.Component;

@Component
public class PagoMapper {

    public PagoDto toDto(Pago pago) {
        PagoDto dto = new PagoDto();
        dto.setId(pago.getId());
        dto.setIdVenta(pago.getVenta().getId());
        dto.setMetodoPago(pago.getMetodoPago());
        dto.setMonto(pago.getMonto());
        dto.setFecha(pago.getFecha());
        return dto;
    }

    public Pago toEntity(PagoDto dto) {
        Pago pago = new Pago();
        pago.setMetodoPago(dto.getMetodoPago());
        pago.setMonto(dto.getMonto());
        pago.setFecha(dto.getFecha());
        return pago;
    }
}