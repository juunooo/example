package junwoo.example.domain.user;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import junwoo.example.shared.BaseEntity;
import lombok.Getter;

@Getter
@Entity
@Table(name = "p_user")
public class User extends BaseEntity {
    private String username;

    public static User createUser(UserRequest userRequest) {
        User user = new User();
        user.username = userRequest.username();
        return user;
    }
}
