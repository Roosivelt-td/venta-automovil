package automoviles.auth.dto;

public class AuthResponse {

    private String token;
    private UserData user;

    // Constructor privado
    private AuthResponse(String token, UserData user) {
        this.token = token;
        this.user = user;
    }

    // Getters
    public String getToken() {
        return token;
    }

    public UserData getUser() {
        return user;
    }

    // Builder estático
    public static AuthResponseBuilder builder() {
        return new AuthResponseBuilder();
    }

    // Clase Builder
    public static class AuthResponseBuilder {

        private String token;
        private UserData user;

        public AuthResponseBuilder token(String token) {
            this.token = token;
            return this;
        }

        public AuthResponseBuilder user(UserData user) {
            this.user = user;
            return this;
        }

        public AuthResponse build() {
            return new AuthResponse(this.token, this.user);
        }
    }

    // Clase interna para los datos del usuario
    public static class UserData {
        private String username;
        private String email;
        private String nombre;
        private String apellido;
        private String rol;

        public UserData(String username, String email, String nombre, String apellido, String rol) {
            this.username = username;
            this.email = email;
            this.nombre = nombre;
            this.apellido = apellido;
            this.rol = rol;
        }

        // Getters
        public String getUsername() { return username; }
        public String getEmail() { return email; }
        public String getNombre() { return nombre; }
        public String getApellido() { return apellido; }
        public String getRol() { return rol; }
    }
}
