package automoviles.service;

import automoviles.dto.AutoDto;
import java.util.List;

public interface AutoService {
    AutoDto crearAuto(AutoDto autoDto);
    AutoDto obtenerAutoPorId(Long id);
    List<AutoDto> obtenerTodosLosAutos();
    AutoDto actualizarAuto(Long id, AutoDto autoDto);
    void eliminarAuto(Long id);
}