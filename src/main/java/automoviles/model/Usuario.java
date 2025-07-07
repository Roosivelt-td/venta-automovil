package automoviles.model;

import automoviles.auth.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuarios") // Mapea a la tabla "usuarios" en la DB
@Data // Lombok: genera getters, setters, toString, etc.
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID autoincremental

    private Long id;
    private String nombre;
    private String apellido;
    private String sexo;
    private String direccion;
    private String celular;

    @OneToOne
    @JoinColumn(name = "IdUser")
    private User user;
    //private Boolean estado -- true or false -- ;
    @Column(name = "estado", columnDefinition = "BOOLEAN DEFAULT TRUE")
    private Boolean estado;



}