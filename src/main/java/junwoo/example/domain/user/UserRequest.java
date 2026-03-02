package junwoo.example.domain.user;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.regex.Pattern;

public record UserRequest(
        @NotNull
        @Size(min = 5, max = 20)
        String username
) {
        private static final Pattern USERNAME_PATTERN =
                Pattern.compile("[a-z0-9]+");

        public UserRequest {
                if (!username.isEmpty() && !USERNAME_PATTERN.matcher(username).matches()) {
                        throw new IllegalArgumentException("사용자이름 형식이 올바르지 않습니다." + username);
                }
        }
}
