package automoviles.repository;

import automoviles.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> {
    // Buscar ventas por cliente
    List<Venta> findByClienteNombreContainingIgnoreCase(String nombreCliente);

    // Buscar ventas por auto
    List<Venta> findByAutoMarcaContainingIgnoreCaseOrAutoModeloContainingIgnoreCase(String marca, String modelo);

    // Buscar ventas por usuario
    List<Venta> findByUsuarioNombreContainingIgnoreCase(String nombreUsuario);

    // Buscar ventas por fecha
    List<Venta> findByFechaBetween(LocalDate fechaInicio, LocalDate fechaFin);

    // Buscar ventas por rango de precio
    List<Venta> findByPrecioVentaBetween(BigDecimal precioMin, BigDecimal precioMax);
}