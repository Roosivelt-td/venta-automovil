package automoviles.service.mapper;

import automoviles.dto.response.ProveedorResponse;
import automoviles.model.Proveedor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

@Component
public class ProveedorMapper {

    public  Collection<ProveedorResponse> toListProveedorToProveedorResponse(Collection<Proveedor> listarProveedor){
        Collection<ProveedorResponse> listarProveedorResponse = new ArrayList<ProveedorResponse>();
        if (listarProveedor != null && !listarProveedor.isEmpty()) {
            for (Proveedor proveedor : listarProveedor) {
                ProveedorResponse proveedorResponse = new ProveedorResponse();
                proveedorResponse.setIdentificador(proveedor.getId());
                proveedorResponse.setNombreEmpresa(proveedor.getNombreEmpresa());
                proveedorResponse.setRuc(proveedor.getRuc());
                proveedorResponse.setContacto(proveedor.getContacto());
                proveedorResponse.setTelefono(proveedor.getTelefono());
                proveedorResponse.setDireccion(proveedor.getDireccion());
                listarProveedorResponse.add(proveedorResponse);
            }
        }
        return listarProveedorResponse;
    }
    public ProveedorResponse toProveedorToProveedorResponse(Proveedor proveedor) {
        ProveedorResponse proveedorResponse = new ProveedorResponse();
        if (proveedor != null) {
            proveedorResponse.setNombreEmpresa(proveedor.getNombreEmpresa());
            proveedorResponse.setRuc(proveedor.getRuc());
            proveedorResponse.setContacto(proveedor.getContacto());
            proveedorResponse.setTelefono(proveedor.getTelefono());
            proveedorResponse.setDireccion(proveedor.getDireccion());
        }
        return proveedorResponse;
    }
}