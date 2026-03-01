package junwoo.example.user.presentation;

import jakarta.validation.Valid;
import junwoo.example.shared.ApiResponse;
import junwoo.example.shared.PageResponse;
import junwoo.example.user.application.UserService;
import junwoo.example.user.domain.User;
import junwoo.example.user.application.UserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class UserController {
    private final UserService userService;

    @PostMapping("/api/users")
    public ResponseEntity<ApiResponse<UUID>> createUser(@RequestBody @Valid UserRequest userRequest) {
        User user = userService.createUser(userRequest);
        return ApiResponse.create(user.getId());
    }

    @GetMapping("/api/users")
    public ResponseEntity<ApiResponse<PageResponse<UserResponse>>> getUser(
            @PageableDefault(sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        Slice<UserResponse> users = userService.getUsers(pageable).map(
                user -> new UserResponse(user.getId())
        );

        return ApiResponse.ok(PageResponse.of(users));
    }

    @GetMapping("/api/users/{userId}")
    public ResponseEntity<ApiResponse<UserResponse>> getUser(@PathVariable UUID userId) {
        User user = userService.getUser(userId);
        return ApiResponse.ok(new UserResponse(user.getId()));
    }
}
