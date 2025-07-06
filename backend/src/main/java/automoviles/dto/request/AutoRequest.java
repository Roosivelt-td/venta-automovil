package automoviles.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

        @Getter //Genera automáticamente métodos como getAuto().
        @Setter //Genera métodos como setAuto(Auto auto).
        public class AutoRequest {
            private Long id;
            private String marca;
            private String modelo;
            private Integer anio;
            private String color;
            private Integer kilometraje;
            private String tipoCombustible;
            private String transmision;
            private Integer cilindrada;
            private Integer potencia;
            private Integer stock;
            private BigDecimal precio;
            private String descripcion;
            private String imagenUrl;
            private String estado;
        }