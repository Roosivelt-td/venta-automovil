package automoviles.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "usuarios") // Mapea a la tabla "usuarios" en la DB
@Data // Lombok: genera getters, setters, toString, etc.
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID autoincremental

    private Long id;
    private String nombre;
    private String apellido;
    private String rol;

    //private Boolean estado -- true or false -- ;
    @Column(name = "estado", columnDefinition = "BOOLEAN DEFAULT TRUE")
    private Boolean estado;
                             
    @Column(name = "correo", unique = true, nullable = false) // Columna "correo", única y no nula
    private String correo;

    @Column(nullable = false, length = 60) // Longitud para bcrypt
    private String contrasena;


}