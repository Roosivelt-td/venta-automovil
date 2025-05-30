package automoviles.service.impl;

import automoviles.dto.request.AutoRequest;
import automoviles.dto.response.AutoResponse;
import automoviles.model.Auto;
import automoviles.repository.AutoRepository;
import automoviles.service.AutoService;
import automoviles.service.mapper.AutoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;


@Service
public class AutoServiceImpl implements AutoService {

    @Autowired
    private AutoRepository autoRepository;

    @Autowired
    private AutoMapper autoMapper;

    @Override // crear auto
    public void crearAuto(AutoRequest request) {
        Auto autoNew = new Auto();
        System.out.println("Nuevo auto" + autoNew);
        autoNew.setMarca(request.getMarca());
        autoNew.setModelo(request.getModelo());
        autoNew.setAnio(request.getAnio());
        autoNew.setColor(request.getColor());
        autoNew.setKilometraje(request.getKilometraje());
        autoNew.setTipo(request.getTipo());
        autoNew.setPrecio(request.getPrecio());
        autoNew.setDescripcion(request.getDescripcion());
        autoNew.setImagenUrl(request.getImagenUrl());
        autoNew.setEstado("Disponible");
        autoRepository.save(autoNew);
    }
    @Override // obtener autos por id
    public AutoResponse obtenerAutoPorId(Long id) {
        Auto auto = autoRepository.findById(id).orElse(null);
        if (auto == null) {
            throw new RuntimeException("Auto no encontrado con ID: " + id);
        }
        return autoMapper.toAutoToAutoResponse(auto);
    }

    @Override // obtener todos los autos
    public Collection<AutoResponse> obtenerTodosLosAutos() {
        Collection<Auto> listAutoResponse = autoRepository.findAll();
        return autoMapper.toListAutoToAutoResponse(listAutoResponse);
    }

    @Override //
    public void actualizarAuto(Long id, AutoRequest request) {
        Auto auto = autoRepository.findById(id).orElse(null);
        if (auto != null) {
            auto.setMarca(request.getMarca());
            auto.setModelo(request.getModelo());
            auto.setAnio(request.getAnio());
            auto.setColor(request.getColor());
            auto.setKilometraje(request.getKilometraje());
            auto.setTipo(request.getTipo());
            auto.setPrecio(request.getPrecio());
            auto.setDescripcion(request.getDescripcion());
            auto.setImagenUrl(request.getImagenUrl());
            auto.setEstado(request.getEstado());
            autoRepository.save(auto);
        }else {
            System.out.println("Auto no encontrado");
        }
    }

    @Override
    public void eliminarAuto(Long id) {
        Auto auto = autoRepository.findById(id).orElse(null);
        if (auto != null) {
            autoRepository.delete(auto);
        }else {
            System.out.println("Auto no encontrado");
        }
    }

}