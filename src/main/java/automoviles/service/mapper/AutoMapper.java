package automoviles.service.mapper;

import automoviles.dto.response.AutoResponse;
import automoviles.model.Auto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

@Component
public class AutoMapper {

    public Collection<AutoResponse> toListAutoToAutoResponse(Collection<Auto> listarAuto) {
        Collection<AutoResponse> listarAutoResponse = new ArrayList<AutoResponse>();
        if (listarAuto != null && !listarAuto.isEmpty()) {
            for (Auto auto : listarAuto) {
                AutoResponse autoResponse = new AutoResponse();
                autoResponse.setIdentificador(auto.getId());
                autoResponse.setMarca(auto.getMarca());
                autoResponse.setModelo(auto.getModelo());
                autoResponse.setAnio(auto.getAnio());
                autoResponse.setColor(auto.getColor());
                autoResponse.setKilometraje(auto.getKilometraje());
                autoResponse.setTipo(auto.getTipo());
                autoResponse.setPrecio(auto.getPrecio());
                autoResponse.setDescripcion(auto.getDescripcion());
                autoResponse.setImagenUrl(auto.getImagenUrl());
                autoResponse.setEstado(auto.getEstado());
                listarAutoResponse.add(autoResponse);
            }
        }
        return listarAutoResponse;
    }

    public AutoResponse toAutoToAutoResponse(Auto auto) {
        AutoResponse autoResponse = new AutoResponse();
        if (auto != null) {
            autoResponse.setMarca(auto.getMarca());
            autoResponse.setModelo(auto.getModelo());
            autoResponse.setAnio(auto.getAnio());
            autoResponse.setColor(auto.getColor());
            autoResponse.setKilometraje(auto.getKilometraje());
            autoResponse.setTipo(auto.getTipo());
            autoResponse.setPrecio(auto.getPrecio());
            autoResponse.setDescripcion(auto.getDescripcion());
            autoResponse.setImagenUrl(auto.getImagenUrl());
            autoResponse.setEstado(auto.getEstado());
        }
        return autoResponse;
    }

}