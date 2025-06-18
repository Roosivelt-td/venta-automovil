package automoviles.service;

import automoviles.dto.request.AutoRequest;
import automoviles.dto.response.AutoResponse;

import java.util.Collection;

public interface AutoService {

    Collection<AutoResponse> obtenerTodosLosAutos();
    AutoResponse obtenerAutoPorId(Long id);
    void crearAuto(AutoRequest request);
    void actualizarAuto(Long id, AutoRequest request);
    void eliminarAuto(Long id);
    Collection<AutoResponse> obtenerAutosPorMarca(String marca);
}