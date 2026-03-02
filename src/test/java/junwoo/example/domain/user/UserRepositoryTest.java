package junwoo.example.domain.user;

import jakarta.persistence.EntityManager;
import junwoo.example.shared.JpaAuditConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@Import(JpaAuditConfig.class)
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    EntityManager entityManager;

    @Test
    void createUser() {
        var user = User.createUser(new UserRequest("username"));

        assertThat(user.getId()).isNull();

        userRepository.save(user);

        assertThat(user.getId()).isNotNull();

        entityManager.flush();
        entityManager.clear();

        var foundUser = userRepository.findById(user.getId()).orElseThrow();
        assertThat(foundUser.getUsername()).isEqualTo(user.getUsername());
        assertThat(foundUser.getCreatedAt()).isNotNull();
    }

    @Test
    void findAll() {
        var user1 = User.createUser(new UserRequest("username1"));
        var user2 = User.createUser(new UserRequest("username2"));

        var pageable = PageRequest.of(0, 10, Sort.by("createdAt").descending());

        userRepository.save(user1);
        userRepository.save(user2);

        entityManager.flush();
        entityManager.clear();

        var users = userRepository.findAll(pageable);

        assertThat(users).hasSize(2);
        assertThat(users.getContent().get(0).getUsername()).isEqualTo(user2.getUsername());
        assertThat(users.getContent().get(1).getUsername()).isEqualTo(user1.getUsername());
        assertThat(users.hasNext()).isFalse();
    }

    @Test
    void findById() {
        var user = User.createUser(new UserRequest("username1"));

        userRepository.save(user);

        entityManager.flush();
        entityManager.clear();

        var foundUser = userRepository.findById(user.getId()).orElseThrow();

        assertThat(foundUser.getId()).isEqualTo(user.getId());
        assertThat(foundUser.getUsername()).isEqualTo(user.getUsername());
    }
}