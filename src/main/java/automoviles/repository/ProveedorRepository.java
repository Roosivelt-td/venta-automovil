package automoviles.repository;

import automoviles.model.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor, Long> {
    Proveedor findByRuc(String ruc); // Búsqueda personalizada por RUC
}