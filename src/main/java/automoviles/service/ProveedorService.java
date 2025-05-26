package automoviles.service;

import java.util.List;

public interface ProveedorService {
    ProveedorDto crearProveedor(ProveedorDto proveedorDto);
    ProveedorDto obtenerProveedorPorId(Long id);
    List<ProveedorDto> obtenerTodosLosProveedores();
    ProveedorDto actualizarProveedor(Long id, ProveedorDto proveedorDto);
    void eliminarProveedor(Long id);
}