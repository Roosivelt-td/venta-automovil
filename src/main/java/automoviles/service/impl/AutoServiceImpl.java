package automoviles.service.impl;

import automoviles.dto.AutoDto;
import automoviles.model.Auto;
import automoviles.repository.AutoRepository;
import automoviles.service.AutoService;
import automoviles.service.mapper.AutoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AutoServiceImpl implements AutoService {

    @Autowired
    private AutoRepository autoRepository;

    @Autowired
    private AutoMapper autoMapper;

    @Override
    public AutoDto crearAuto(AutoDto autoDto) {
        Auto auto = autoMapper.toEntity(autoDto);
        auto = autoRepository.save(auto);
        return autoMapper.toDto(auto);
    }

    @Override
    public AutoDto obtenerAutoPorId(Long id) {
        Auto auto = autoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Auto no encontrado"));
        return autoMapper.toDto(auto);
    }

    @Override
    public List<AutoDto> obtenerTodosLosAutos() {
        return autoRepository.findAll().stream()
                .map(autoMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public AutoDto actualizarAuto(Long id, AutoDto autoDto) {
        Auto auto = autoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Auto no encontrado"));
        auto.setMarca(autoDto.getMarca());
        auto.setModelo(autoDto.getModelo());
        auto.setAnio(autoDto.getAnio());
        auto.setColor(autoDto.getColor());
        auto.setKilometraje(autoDto.getKilometraje());
        auto.setTipo(autoDto.getTipo());
        auto.setPrecio(autoDto.getPrecio());
        auto.setDescripcion(autoDto.getDescripcion());
        auto.setImagenUrl(autoDto.getImagenUrl());
        auto.setEstado(autoDto.getEstado());
        auto = autoRepository.save(auto);
        return autoMapper.toDto(auto);
    }

    @Override
    public void eliminarAuto(Long id) {
        autoRepository.deleteById(id);
    }
}