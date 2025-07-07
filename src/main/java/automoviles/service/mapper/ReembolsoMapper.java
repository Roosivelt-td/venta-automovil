package automoviles.service.mapper;

import automoviles.dto.response.ReembolsoResponse;
import automoviles.model.Reembolso;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

@Component
public class ReembolsoMapper {

    public  Collection<ReembolsoResponse> toListReembolsoToReembolsoResponse(Collection<Reembolso> listarReembolso) {
        Collection<ReembolsoResponse> listarReembolsoResponse = new ArrayList<ReembolsoResponse>();
        if (listarReembolso != null &&  !listarReembolso.isEmpty()) {
            for (Reembolso reembolso : listarReembolso) {
                ReembolsoResponse reembolsoResponse = new ReembolsoResponse();
                reembolsoResponse.setIdentificador(reembolso.getId());
                reembolsoResponse.setIdVenta(reembolso.getVenta().getId());
                reembolsoResponse.setMotivo(reembolso.getMotivo());
                reembolsoResponse.setMonto(reembolso.getMonto());
                reembolsoResponse.setFecha(reembolso.getFecha());
                listarReembolsoResponse.add(reembolsoResponse);
            }
        }
        return listarReembolsoResponse;
    }
    public ReembolsoResponse toReembolsoToReembolsoResponse(Reembolso reembolso) {
        ReembolsoResponse reembolsoResponse = new ReembolsoResponse();
        if (reembolso != null) {
            reembolsoResponse.setIdVenta(reembolso.getVenta().getId());
            reembolsoResponse.setMotivo(reembolso.getMotivo());
            reembolsoResponse.setMonto(reembolso.getMonto());
            reembolsoResponse.setFecha(reembolso.getFecha());
        }
        return reembolsoResponse;
    }
}