package automoviles.service.impl;

import automoviles.dto.request.ProveedorRequest;
import automoviles.dto.response.ProveedorResponse;
import automoviles.model.Proveedor;
import automoviles.repository.ProveedorRepository;
import automoviles.service.ProveedorService;
import automoviles.service.mapper.ProveedorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ProveedorServiceImpl implements ProveedorService {

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Autowired
    private ProveedorMapper proveedorMapper;

    @Override //registro de proveedor

    public void crearProveedor(ProveedorRequest  request) {
        Proveedor  proveedorNew= new Proveedor();
        System.out.println("Nuevo proveedor" + proveedorNew);
        proveedorNew.setNombreEmpresa(request.getNombreEmpresa());
        proveedorNew.setRuc(request.getRuc());
        proveedorNew.setContacto(request.getContacto());
        proveedorNew.setTelefono(request.getTelefono());
        proveedorNew.setDireccion(request.getDireccion());
        proveedorRepository.save(proveedorNew);
    }

    @Override // buscar proveedor por id
    public ProveedorResponse obtenerProveedorPorId(Long id) {
        Proveedor proveedor = proveedorRepository.findById(id).orElse(null);
        return proveedorMapper.toProveedorToProveedorResponse(proveedor);
    }

    @Override // obtener todos los proveedors
    public Collection<ProveedorResponse> obtenerTodosLosProveedors() {
        Collection<Proveedor> listProveedorResponse = proveedorRepository.findAll();
        return proveedorMapper.toListProveedorToProveedorResponse(listProveedorResponse);
    }

    @Override // actualizar proveedor
    public void actualizarProveedor(Long id, ProveedorRequest request) {
        Proveedor proveedor = proveedorRepository.findById(id).orElse(null);
        if (proveedor != null) {
            proveedor.setNombreEmpresa(request.getNombreEmpresa());
            proveedor.setRuc(request.getRuc());
            proveedor.setContacto(request.getContacto());
            proveedor.setTelefono(request.getTelefono());
            proveedor.setDireccion(request.getDireccion());
            proveedorRepository.save(proveedor);
        }
    }

    @Override // eliminar proveedor
    public void eliminarProveedor(Long id) {
        Proveedor proveedor = proveedorRepository.findById(id).orElse(null);
        if (proveedor != null) {
            proveedorRepository.delete(proveedor);
        }
    }
}