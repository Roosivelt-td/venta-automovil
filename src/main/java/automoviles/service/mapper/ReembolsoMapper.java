package automoviles.service.mapper;

import automoviles.model.Reembolso;
import org.springframework.stereotype.Component;

@Component
public class ReembolsoMapper {

    public ReembolsoDto toDto(Reembolso reembolso) {
        ReembolsoDto dto = new ReembolsoDto();
        dto.setId(reembolso.getId());
        dto.setIdVenta(reembolso.getVenta().getId());
        dto.setMotivo(reembolso.getMotivo());
        dto.setMonto(reembolso.getMonto());
        dto.setFecha(reembolso.getFecha());
        return dto;
    }

    public Reembolso toEntity(ReembolsoDto dto) {
        Reembolso reembolso = new Reembolso();
        reembolso.setMotivo(dto.getMotivo());
        reembolso.setMonto(dto.getMonto());
        reembolso.setFecha(dto.getFecha());
        return reembolso;
    }
}