package automoviles.service.mapper;

import automoviles.dto.response.VentaResponse;
import automoviles.dto.response.ClienteResponse;
import automoviles.dto.response.AutoResponse;
import automoviles.dto.response.UsuarioResponse;
import automoviles.model.Venta;
import automoviles.model.Cliente;
import automoviles.model.Auto;
import automoviles.model.Usuario;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

@Component
public class VentaMapper {

    public Collection<VentaResponse> toListVentaToVentaResponse(Collection<Venta> listarVenta) {
        Collection<VentaResponse> listarVentaResponse = new ArrayList<VentaResponse>();
        if (listarVenta != null && !listarVenta.isEmpty()) {
            for (Venta venta : listarVenta) {
                VentaResponse ventaResponse = toVentaToVentaResponse(venta);
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
            // Mapear Cliente
            Cliente cliente = venta.getCliente();
            if (cliente != null) {
                ClienteResponse clienteResponse = new ClienteResponse();
                clienteResponse.setIdentificador(cliente.getId());
                clienteResponse.setNombre(cliente.getNombre());
                clienteResponse.setDni(cliente.getDni());
                clienteResponse.setTelefono(cliente.getTelefono());
                clienteResponse.setDireccion(cliente.getDireccion());
                clienteResponse.setCorreo(cliente.getCorreo());
                ventaResponse.setCliente(clienteResponse);
            }
            // Mapear Auto
            Auto auto = venta.getAuto();
            if (auto != null) {
                AutoResponse autoResponse = new AutoResponse();
                autoResponse.setId(auto.getId());
                autoResponse.setMarca(auto.getMarca());
                autoResponse.setModelo(auto.getModelo());
                autoResponse.setAnio(auto.getAnio());
                autoResponse.setColor(auto.getColor());
                autoResponse.setKilometraje(auto.getKilometraje());
                autoResponse.setTipoCombustible(auto.getTipoCombustible());
                autoResponse.setTransmision(auto.getTransmision());
                autoResponse.setCilindrada(auto.getCilindrada());
                autoResponse.setPotencia(auto.getPotencia());
                autoResponse.setStock(auto.getStock());
                autoResponse.setPrecio(auto.getPrecio());
                autoResponse.setDescripcion(auto.getDescripcion());
                autoResponse.setImagenUrl(auto.getImagenUrl());
                autoResponse.setEstado(auto.getEstado());
                ventaResponse.setAuto(autoResponse);
            }
            // Mapear Usuario
            Usuario usuario = venta.getUsuario();
            if (usuario != null) {
                UsuarioResponse usuarioResponse = new UsuarioResponse();
                usuarioResponse.setIdentificador(usuario.getId());
                usuarioResponse.setNombre(usuario.getNombre());
                usuarioResponse.setApellido(usuario.getApellido());
                usuarioResponse.setSexo(usuario.getSexo());
                usuarioResponse.setDireccion(usuario.getDireccion());
                usuarioResponse.setCelular(usuario.getCelular());
                usuarioResponse.setEstado(usuario.getEstado());
                // usuarioResponse.setIdUser(usuario.getUser() != null ? usuario.getUser().getId() : null);
                ventaResponse.setUsuario(usuarioResponse);
            }
        }
        return ventaResponse;
    }
}