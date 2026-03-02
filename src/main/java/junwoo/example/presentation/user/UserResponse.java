package junwoo.example.presentation.user;

import java.util.UUID;

public record UserResponse(
        UUID userId
) {
    public static UserResponse of (UUID userId) {
        return new UserResponse(userId);
    }
}
