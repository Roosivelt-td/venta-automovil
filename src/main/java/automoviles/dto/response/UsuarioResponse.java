package automoviles.dto.response;

import lombok.Data;

@Data
public class UsuarioResponse {

    private Long identificador ;
    private String nombre;
    private String apellido;
    private String sexo;
    private String direccion;
    private String celular;
    private Boolean estado;
    private Long idUser;

}