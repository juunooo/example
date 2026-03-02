package junwoo.example.application.user;

import jakarta.persistence.EntityManager;
import junwoo.example.domain.user.UserRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class UserServiceTest {
    @Autowired
    UserService userService;

    @Autowired
    EntityManager entityManager;

    @Test
    void createUser() {
        var request = new UserRequest("username");
        var user = userService.createUser(request);

        userService.createUser(request);

        entityManager.flush();

        assertThat(user.getId()).isNotNull();
        assertThat(user.getUsername()).isEqualTo(request.username());
        assertThat(user.getCreatedAt()).isNotNull();
//        assertThat(user.getCreatedBy()).isNotNull(); Todo: AuditorAwareImpl 설정 해야함
    }

    @Test
    void getUsers() {
        var user1 = userService.createUser(new UserRequest("username1"));
        var user2 = userService.createUser(new UserRequest("username2"));

        entityManager.flush();
        entityManager.clear();

        var pageable = PageRequest.of(0, 10, Sort.by("createdAt").descending());

        var users = userService.getUsers(pageable);

        assertThat(users.getContent()).hasSize(2);
        assertThat(users.getContent().get(0).getUsername()).isEqualTo(user2.getUsername());
        assertThat(users.getContent().get(1).getUsername()).isEqualTo(user1.getUsername());
        assertThat(users.hasNext()).isFalse();
    }

    @Test
    void getUser() {
        var user = userService.createUser(new UserRequest("username1"));

        entityManager.flush();
        entityManager.clear();

        var foundUser = userService.getUser(user.getId());

        assertThat(foundUser.getId()).isEqualTo(user.getId());
        assertThat(foundUser.getUsername()).isEqualTo(user.getUsername());
    }

}