package automoviles.repository;

import automoviles.model.Auto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
//Indica que esta interfaz es un componente de Spring encargado de la persistencia.
//Spring lo detecta automáticamente y lo inyecta donde se use (@Autowired).

public interface AutoRepository extends JpaRepository<Auto, Long> {
    List<Auto> findByMarca(String marca);
    List<Auto> findByMarcaAndModelo(String marca, String modelo);
}
