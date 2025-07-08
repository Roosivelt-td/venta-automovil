package automoviles.repository;

import automoviles.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Collection;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Collection<Cliente> findByDni(Integer dni);
}