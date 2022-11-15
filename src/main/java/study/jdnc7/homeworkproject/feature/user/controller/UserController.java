package study.jdnc7.homeworkproject.feature.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import study.jdnc7.homeworkproject.domain.user.model.User;
import study.jdnc7.homeworkproject.feature.user.model.UserDto;
import study.jdnc7.homeworkproject.feature.user.model.UserRequest;
import study.jdnc7.homeworkproject.feature.user.service.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<Long> signup(@Valid @RequestBody UserRequest userRequest) {
        return ResponseEntity.ok(userService.signup(userRequest));
    }

    @GetMapping("/my")
    public ResponseEntity<User> getMyUserInfo() {
        return ResponseEntity.ok(userService.getMyUserWithAuthorities().get());
    }

    @GetMapping("/{userName}")
    @PreAuthorize("hasRole('ADMIN')") //앞에 hasRole(이것이 무조건 있어야한다)이나 hasAnyRole(이것중 하나라도 잇으면)의 경우 Role_의 접두사를 앞에 붙여준다
    public ResponseEntity<User> getUserInfo(@PathVariable String userName) {
        return ResponseEntity.ok(userService.getUserWithAuthorities(userName).get());
    }
}
