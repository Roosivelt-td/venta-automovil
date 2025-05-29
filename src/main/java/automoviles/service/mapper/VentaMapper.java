package automoviles.service.mapper;

import automoviles.dto.response.VentaResponse;
import automoviles.model.Venta;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

@Component
public class VentaMapper {

    public Collection<VentaResponse> toListVentaToVentaResponse(Collection<Venta> listarVenta) {
        Collection<VentaResponse> listarVentaResponse = new ArrayList<VentaResponse>();
        if (listarVenta != null && !listarVenta.isEmpty()) {
            for (Venta venta : listarVenta) {
                VentaResponse ventaResponse = new VentaResponse();
                ventaResponse.setIdentificador(venta.getId());
                ventaResponse.setIdUsuario(venta.getUsuario().getId());
                ventaResponse.setIdCliente(venta.getCliente().getId());
                ventaResponse.setIdAuto(venta.getAuto().getId());
                ventaResponse.setFecha(venta.getFecha());
                ventaResponse.setPrecioVenta(venta.getPrecioVenta());
                listarVentaResponse.add(ventaResponse);

            }
        }
        return listarVentaResponse;
    }
    public VentaResponse toVentaToVentaResponse(Venta venta) {
        VentaResponse ventaResponse = new VentaResponse();
        if (venta != null) {
            ventaResponse.setIdentificador(venta.getId());
            ventaResponse.setFecha(venta.getFecha());
            ventaResponse.setPrecioVenta(venta.getPrecioVenta());
            ventaResponse.setIdAuto(venta.getAuto().getId());
            ventaResponse.setIdCliente(venta.getCliente().getId());
            ventaResponse.setIdUsuario(venta.getUsuario().getId());
        }
        return ventaResponse;
    }
}