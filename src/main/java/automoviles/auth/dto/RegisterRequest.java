package automoviles.auth.dto;

import lombok.Data;

@Data
public class RegisterRequest {
    private String username;
    private String password;
    private String email;

    // Datos personales
    private String nombre;
    private String apellido;
    private String sexo;
    private String direccion;
    private String celular;
}