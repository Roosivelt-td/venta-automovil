package automoviles.service.mapper;

import automoviles.dto.response.PagoResponse;
import automoviles.dto.response.UsuarioResponse;
import automoviles.model.Pago;
import automoviles.model.Usuario;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

@Component
public class PagoMapper {

    public Collection<PagoResponse> toListPagoToPagoResponse(Collection<Pago> listarPago) {
        Collection<PagoResponse> listarPagoResponse = new ArrayList<PagoResponse>();
        if (listarPago != null && !listarPago.isEmpty()) {
            for (Pago pago : listarPago) {
                PagoResponse pagoResponse = new PagoResponse();
                pagoResponse.setIdentificador(pago.getId());
                pagoResponse.setIdVenta(pago.getVenta().getId());
                pagoResponse.setMetodoPago(pago.getMetodoPago());
                pagoResponse.setMonto(pago.getMonto());
                pagoResponse.setFecha(pago.getFecha());
                listarPagoResponse.add(pagoResponse);
            }
        }
        return listarPagoResponse;
    }
    public PagoResponse toPagoToPagoResponse(Pago pago) {
        PagoResponse pagoResponse = new PagoResponse();
        if (pago != null) {
            pagoResponse.setIdVenta(pago.getVenta().getId());
            pagoResponse.setMetodoPago(pago.getMetodoPago());
            pagoResponse.setMonto(pago.getMonto());
            pagoResponse.setFecha(pago.getFecha());
        }
        return pagoResponse;
    }
}