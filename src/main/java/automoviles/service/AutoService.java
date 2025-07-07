package automoviles.service;

import automoviles.dto.request.AutoRequest;
import automoviles.dto.response.AutoResponse;

import java.util.Collection;

/**
 * Interfaz para la lógica de negocio de automóviles.
 * Define las operaciones disponibles.
 */
public interface AutoService {

    Collection<AutoResponse> obtenerTodosLosAutos();
    AutoResponse obtenerAutoPorId(Long id);
    void crearAuto(AutoRequest request);
    void actualizarAuto(Long id, AutoRequest request);
    void eliminarAuto(Long id);
    Collection<AutoResponse> obtenerAutosPorMarca(String marca);
    Collection<AutoResponse> obtenerAutosPorMarcaYModelo(String marca, String modelo);
    void actualizarStock(Long idAuto, Integer cantidadVendida);
}