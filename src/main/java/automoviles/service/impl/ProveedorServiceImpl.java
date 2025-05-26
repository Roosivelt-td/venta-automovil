package automoviles.service.impl;

import automoviles.model.Proveedor;
import automoviles.repository.ProveedorRepository;
import automoviles.service.ProveedorService;
import automoviles.service.mapper.ProveedorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProveedorServiceImpl implements ProveedorService {

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Autowired
    private ProveedorMapper proveedorMapper;

    @Override
    public ProveedorDto crearProveedor(ProveedorDto proveedorDto) {
        Proveedor proveedor = proveedorMapper.toEntity(proveedorDto);
        proveedor = proveedorRepository.save(proveedor);
        return proveedorMapper.toDto(proveedor);
    }

    @Override
    public ProveedorDto obtenerProveedorPorId(Long id) {
        Proveedor proveedor = proveedorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado"));
        return proveedorMapper.toDto(proveedor);
    }

    @Override
    public List<ProveedorDto> obtenerTodosLosProveedores() {
        return proveedorRepository.findAll().stream()
                .map(proveedorMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProveedorDto actualizarProveedor(Long id, ProveedorDto proveedorDto) {
        Proveedor proveedor = proveedorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado"));
        proveedor.setNombreEmpresa(proveedorDto.getNombreEmpresa());
        proveedor.setRuc(proveedorDto.getRuc());
        proveedor.setContacto(proveedorDto.getContacto());
        proveedor.setTelefono(proveedorDto.getTelefono());
        proveedor.setDireccion(proveedorDto.getDireccion());
        proveedor = proveedorRepository.save(proveedor);
        return proveedorMapper.toDto(proveedor);
    }

    @Override
    public void eliminarProveedor(Long id) {
        proveedorRepository.deleteById(id);
    }
}