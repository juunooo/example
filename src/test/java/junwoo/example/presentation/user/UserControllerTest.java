package junwoo.example.presentation.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import junwoo.example.application.user.UserService;
import junwoo.example.domain.user.UserRepository;
import junwoo.example.domain.user.UserRequest;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.assertj.MockMvcTester;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class UserControllerTest {
    @Autowired
    MockMvcTester mvcTester;

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ObjectMapper objectMapper;


    @Test
    void createUser() throws JsonProcessingException, UnsupportedEncodingException {
        var request = new UserRequest("username");
        var requestJson = objectMapper.writeValueAsString(request);

        var result = mvcTester.post().uri("/api/users").contentType(MediaType.APPLICATION_JSON)
                .content(requestJson).exchange();

        assertThat(result)
                .apply(print())
                .hasStatus(HttpStatus.CREATED)
                .bodyJson()
                .hasPathSatisfying("$.data", value -> assertThat(value).isNotNull());

        var id = UUID.fromString(JsonPath.read(result.getResponse().getContentAsString(), "$.data").toString());

        var user = userRepository.findById(id).orElseThrow();

        assertThat(user.getId()).isEqualTo(id);
        assertThat(user.getUsername()).isEqualTo(request.username());
    }

    @Test
    void getUsers() {
        var user1 = new UserRequest("username1");
        var user2 = new UserRequest("username2");

        userService.createUser(user1);
        userService.createUser(user2);

        var result = mvcTester.get().uri("/api/users").contentType(MediaType.APPLICATION_JSON)
                .exchange();

        assertThat(result)
                .apply(print())
                .hasStatusOk()
                .bodyJson()
                .hasPathSatisfying("$.data.contents", value ->
                        assertThat(value).asInstanceOf(InstanceOfAssertFactories.LIST).hasSize(2))
                .hasPathSatisfying("$.data.contents",
                        value ->
                            assertThat(value).asInstanceOf(InstanceOfAssertFactories.LIST).hasSize(2)
                )
                .hasPathSatisfying("$.data.hasNext", value ->
                        assertThat(value).asBoolean().isFalse());

    }

    @Test
    void getUser() {
        var userRequest = new UserRequest("username");

        var createdUser = userService.createUser(userRequest);

        var result = mvcTester.get().uri("/api/users/{userId}",
                createdUser.getId()).contentType(MediaType.APPLICATION_JSON).exchange();

        assertThat(result)
                .apply(print())
                .hasStatusOk()
                .bodyJson()
                .hasPathSatisfying("$.data.userId", value ->
                {
                    Assertions.assertNotNull(createdUser.getId());
                    assertThat(value).isEqualTo(createdUser.getId().toString());
                });
    }
}