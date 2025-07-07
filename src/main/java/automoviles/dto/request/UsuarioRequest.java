package automoviles.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioRequest {
    private Long id;
    private String nombre;
    private String apellido;
    private String sexo;
    private String direccion;
    private String celular;
    private Boolean estado;
    private Long idUser;

}