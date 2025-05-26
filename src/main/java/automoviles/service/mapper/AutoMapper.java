package automoviles.service.mapper;

import automoviles.dto.AutoDto;
import automoviles.model.Auto;
import org.springframework.stereotype.Component;

@Component
public class AutoMapper {

    public AutoDto toDto(Auto auto) {
        AutoDto dto = new AutoDto();
        dto.setId(auto.getId());
        dto.setMarca(auto.getMarca());
        dto.setModelo(auto.getModelo());
        dto.setAnio(auto.getAnio());
        dto.setColor(auto.getColor());
        dto.setKilometraje(auto.getKilometraje());
        dto.setTipo(auto.getTipo());
        dto.setPrecio(auto.getPrecio());
        dto.setDescripcion(auto.getDescripcion());
        dto.setImagenUrl(auto.getImagenUrl());
        dto.setEstado(auto.getEstado());
        return dto;
    }

    public Auto toEntity(AutoDto dto) {
        Auto auto = new Auto();
        auto.setMarca(dto.getMarca());
        auto.setModelo(dto.getModelo());
        auto.setAnio(dto.getAnio());
        auto.setColor(dto.getColor());
        auto.setKilometraje(dto.getKilometraje());
        auto.setTipo(dto.getTipo());
        auto.setPrecio(dto.getPrecio());
        auto.setDescripcion(dto.getDescripcion());
        auto.setImagenUrl(dto.getImagenUrl());
        auto.setEstado(dto.getEstado());
        return auto;
    }
}