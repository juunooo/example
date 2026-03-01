package junwoo.example.user.application;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserRequest(
        @NotNull
        @Size(min = 5, max = 20)
        String username
) {
}
