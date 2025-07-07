package automoviles.repository;

import automoviles.model.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor, Long> {
    List<Proveedor> findByNombreEmpresaContainingIgnoreCaseOrRucContainingIgnoreCase(String nombreEmpresa, String ruc);

}