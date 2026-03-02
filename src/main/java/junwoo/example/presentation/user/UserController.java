package junwoo.example.presentation.user;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import junwoo.example.application.user.UserService;
import junwoo.example.domain.user.User;
import junwoo.example.domain.user.UserRequest;
import junwoo.example.shared.ApiResponse;
import junwoo.example.shared.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@Tag(name = "UserController", description = "사용자 관련 API")
public class UserController {
    private final UserService userService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/api/users")
    public ApiResponse<UUID> createUser(@RequestBody @Valid UserRequest userRequest) {
        User user = userService.createUser(userRequest);
        return ApiResponse.of(user.getId());
    }

    @GetMapping("/api/users")
    public ApiResponse<PageResponse<UserResponse>> getUsers(
            @PageableDefault(sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        Slice<UserResponse> users = userService.getUsers(pageable).map(
                user -> new UserResponse(user.getId())
        );

        return ApiResponse.of(PageResponse.of(users));
    }

    @GetMapping("/api/users/{userId}")
    public ApiResponse<UserResponse> getUser(@PathVariable UUID userId) {
        User user = userService.getUser(userId);
        return ApiResponse.of(new UserResponse(user.getId()));
    }
}
