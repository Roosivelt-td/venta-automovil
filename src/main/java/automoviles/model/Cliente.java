package automoviles.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "clientes")
@Data
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String nombre;
    @Column(name = "dni", unique = true, nullable = false)
    private String dni;
    private String telefono;
    private String direccion;
    @Column(name = "correo", unique = true, nullable = false)
    private String correo;
}