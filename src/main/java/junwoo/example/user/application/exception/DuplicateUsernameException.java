package junwoo.example.user.application.exception;

import lombok.Getter;

@Getter
public class DuplicateUsernameException extends RuntimeException {
    public DuplicateUsernameException(String message) {
        super(message);
    }
}
