package automoviles.repository;

import automoviles.model.Pago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PagoRepository extends JpaRepository<Pago, Long> {
    List<Pago> findByVentaId(Long ventaId); // Busca pagos por ID de venta
}