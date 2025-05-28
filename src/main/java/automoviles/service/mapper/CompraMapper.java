package automoviles.service.mapper;

import automoviles.dto.response.CompraResponse;
import automoviles.dto.response.UsuarioResponse;
import automoviles.model.Compra;
import automoviles.model.Usuario;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

@Component
public class CompraMapper {

    public Collection<CompraResponse> toListCompraToCompraResponse(Collection<Compra> listarCompra) {
        Collection<CompraResponse> listarCompraResponse = new ArrayList<CompraResponse>();
        if (listarCompra != null && !listarCompra.isEmpty()) {
            for (Compra compra : listarCompra) {
                CompraResponse compraResponse = new CompraResponse();
                compraResponse.setIdentificador(compra.getId());
                compraResponse.setIdProveedor(compra.getProveedor().getId());
                compraResponse.setIdAuto(compra.getAuto().getId());
                compraResponse.setFecha(compra.getFecha());
                compraResponse.setPrecioCompra(compra.getPrecioCompra());
                listarCompraResponse.add(compraResponse);
            }
        }
        return listarCompraResponse;
    }
    public CompraResponse toCompraToCompraResponse(Compra compra) {
        CompraResponse compraResponse = new CompraResponse();
        if (compra != null) {
            compraResponse.setIdProveedor(compra.getProveedor().getId());
            compraResponse.setIdAuto(compra.getAuto().getId());
            compraResponse.setFecha(compra.getFecha());
            compraResponse.setPrecioCompra(compra.getPrecioCompra());
        }
        return compraResponse;
    }
}