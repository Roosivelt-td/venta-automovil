package automoviles.repository;

import automoviles.model.Reembolso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ReembolsoRepository extends JpaRepository<Reembolso, Long> {
    List<Reembolso> findByVentaId(Long ventaId);  // Busca reembolsos por ID de venta
}