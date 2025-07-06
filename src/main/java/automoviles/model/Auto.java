package automoviles.model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Entity
@Table(name = "autos")
@Data
public class Auto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String marca;
    
    @Column(nullable = false)
    private String modelo;
    
    @Column(nullable = false)
    private Integer anio;
    
    @Column(nullable = false)
    private String color;
    
    @Column(nullable = false)
    private Integer kilometraje;
    
    @Column(name = "tipo_combustible", nullable = false)
    private String tipoCombustible;
    
    @Column(nullable = false)
    private String transmision;
    
    @Column(nullable = false)
    private Integer cilindrada;
    
    @Column(nullable = false)
    private Integer potencia;
    
    @Column(nullable = false)
    private Integer stock;
    
    @Column(name = "precio", nullable = false, precision = 10, scale = 2)
    private BigDecimal precio;
    
    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;
    
    @Column(name = "imagen_url", columnDefinition = "TEXT")
    private String imagenUrl;
    
    @Column(nullable = false)
    private String estado;
}