package automoviles.service;

import automoviles.dto.request.VentaRequest;
import automoviles.dto.response.VentaResponse;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;

public interface VentaService {

    Collection<VentaResponse> obtenerTodosLosVentas();
    VentaResponse obtenerVentaPorId(Long id);
    void crearVenta(VentaRequest request);
    void actualizarVenta(Long id, VentaRequest request);
    void eliminarVenta(Long id);
    
    // Métodos de búsqueda
    Collection<VentaResponse> buscarVentasPorCliente(String nombreCliente);
    Collection<VentaResponse> buscarVentasPorAuto(String marca, String modelo);
    Collection<VentaResponse> buscarVentasPorUsuario(String nombreUsuario);
    Collection<VentaResponse> buscarVentasPorFecha(LocalDate fechaInicio, LocalDate fechaFin);
    Collection<VentaResponse> buscarVentasPorPrecio(BigDecimal precioMin, BigDecimal precioMax);
    Collection<VentaResponse> buscarVentasPorTermino(String termino);
}