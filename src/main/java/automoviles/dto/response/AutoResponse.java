package automoviles.dto.response;

import lombok.Data;

import java.math.BigDecimal;

        @Data // generes como , Getters , toString, equals, hashCode
        public class AutoResponse {
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