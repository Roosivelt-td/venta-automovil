package automoviles.auth.dto;

public class AuthResponse {
    private String token;

    // Constructor privado
    private AuthResponse(String token) {
        this.token = token;
    }

    // Getters
    public String getToken() {
        return token;
    }

    // Builder estático
    public static AuthResponseBuilder builder() {
        return new AuthResponseBuilder();
    }

    // Clase Builder
    public static class AuthResponseBuilder {
        private String token;

        public AuthResponseBuilder token(String token) {
            this.token = token;
            return this;
        }

        public AuthResponse build() {
            return new AuthResponse(this.token);
        }
    }
}