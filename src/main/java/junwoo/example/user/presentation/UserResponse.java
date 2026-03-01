package junwoo.example.user.presentation;

import java.util.UUID;

public record UserResponse(
        UUID userId
) {
    public static UserResponse of (UUID userId) {
        return new UserResponse(userId);
    }
}
