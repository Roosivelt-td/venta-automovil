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


    //Permite que Spring inyecte automáticamente una instancia de una clase (bean)
    // en otra, sin necesidad de crearla manualmente con new,
    // Se usa en campos, constructores o métodos
//>>>>>>> main
    @Autowired
    private AutoRepository autoRepository;

    @Autowired // Inyección del mapeador de autos.
    private AutoMapper autoMapper;

    @Override
    //Asegura que está redefiniendo un método existente (no creando uno nuevo por error).
    // Si el método no existe en la superclase/interfaz, el compilador lanza un error.

    // crear auto
    public void crearAuto(AutoRequest request) {
        Auto autoNew = new Auto();
        System.out.println("INFO: Iniciando la creación de un nuevo auto con datos:" + autoNew);
        
        autoNew.setMarca(request.getMarca());
        autoNew.setModelo(request.getModelo());
        autoNew.setAnio(request.getAnio());
        autoNew.setColor(request.getColor());
        autoNew.setKilometraje(request.getKilometraje());
        autoNew.setTipoCombustible(request.getTipoCombustible());
        autoNew.setTransmision(request.getTransmision());
        autoNew.setCilindrada(request.getCilindrada());
        autoNew.setPotencia(request.getPotencia());
        autoNew.setStock(request.getStock());
        autoNew.setPrecio(request.getPrecio());
        autoNew.setDescripcion(request.getDescripcion());
        autoNew.setImagenUrl(request.getImagenUrl());
        autoNew.setEstado(request.getEstado() != null ? request.getEstado() : "Disponible");
        
        autoRepository.save(autoNew);
        System.out.println("INFO: Auto creado exitosamente: " + autoNew.getMarca() + " " + autoNew.getModelo());
    }
    @Override // obtener autos por id
    public AutoResponse obtenerAutoPorId(Long id) {
        Auto auto = autoRepository.findById(id).orElse(null);
        if (auto == null) {
            throw new RuntimeException("Auto no encontrado con ID: " + id);
        }
        return autoMapper.toAutoToAutoResponse(auto);
    }


    @Override
    public Collection<AutoResponse> obtenerAutosPorMarca(String marca) {
        Collection<Auto> autos = autoRepository.findByMarca(marca);
        if (autos.isEmpty()) {
            throw new RuntimeException("No se encontraron autos de la marca: " + marca);
        }
        return autoMapper.toListAutoToAutoResponse(autos);
    }

    @Override
    public Collection<AutoResponse> obtenerAutosPorMarcaYModelo(String marca, String modelo) {
        Collection<Auto> autos = autoRepository.findByMarcaAndModelo(marca, modelo);
        if (autos.isEmpty()) {
            throw new RuntimeException("No se encontraron autos de la marca " + marca + " y modelo " + modelo);
        }
        return autoMapper.toListAutoToAutoResponse(autos);
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
            auto.setTipoCombustible(request.getTipoCombustible());
            auto.setTransmision(request.getTransmision());
            auto.setCilindrada(request.getCilindrada());
            auto.setPotencia(request.getPotencia());
            auto.setStock(request.getStock());
            auto.setPrecio(request.getPrecio());
            auto.setDescripcion(request.getDescripcion());
            auto.setImagenUrl(request.getImagenUrl());
            auto.setEstado(request.getEstado());
            autoRepository.save(auto);
            System.out.println("INFO: Auto actualizado exitosamente: " + auto.getMarca() + " " + auto.getModelo());
        } else {
            System.out.println("Auto no encontrado");
        }
    }

    @Override
    public void eliminarAuto(Long id) {
        Auto auto = autoRepository.findById(id).orElse(null);
        if (auto != null) {
            autoRepository.delete(auto);
            System.out.println("INFO: Auto eliminado exitosamente: " + auto.getMarca() + " " + auto.getModelo());
        } else {
            System.out.println("Auto no encontrado");
        }
    }

    @Override
    public void actualizarStock(Long idAuto, Integer cantidadVendida) {
        Auto auto = autoRepository.findById(idAuto).orElseThrow(() -> 
            new RuntimeException("Auto no encontrado con ID: " + idAuto));
        
        Integer stockActual = auto.getStock();
        if (stockActual < cantidadVendida) {
            throw new RuntimeException("Stock insuficiente. Stock actual: " + stockActual + ", Cantidad solicitada: " + cantidadVendida);
        }
        
        Integer nuevoStock = stockActual - cantidadVendida;
        auto.setStock(nuevoStock);
        
        // Si el stock llega a 0, cambiar el estado a "Vendido"
        if (nuevoStock == 0) {
            auto.setEstado("Vendido");
        }
        
        autoRepository.save(auto);
        System.out.println("INFO: Stock actualizado para auto ID " + idAuto + ". Stock anterior: " + stockActual + ", Stock nuevo: " + nuevoStock);
    }

}
