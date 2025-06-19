package automoviles.auth.dto;

import lombok.Data;

@Data
public class UpdateUserRequest {
    private String username;
    private String email;
}