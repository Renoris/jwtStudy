package study.jdnc7.homeworkproject.feature.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import study.jdnc7.homeworkproject.feature.user.model.entity.User;
import study.jdnc7.homeworkproject.feature.user.model.dto.UserRequest;
import study.jdnc7.homeworkproject.feature.user.service.UserService;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public Long signup(@Valid @RequestBody UserRequest userRequest) {
        return userService.signup(userRequest);
    }

    @GetMapping("/my")
    @ResponseStatus(HttpStatus.OK)
    public User getMyUserInfo() {
        Optional<User> optionalUser = userService.getMyUserWithAuthorities();
        return optionalUser.orElseThrow(() -> new RuntimeException("해당 유저의 정보가 없습니다."));
    }

    @GetMapping("/{userName}")
    @PreAuthorize("hasRole('ADMIN')") //앞에 hasRole(이것이 무조건 있어야한다)이나 hasAnyRole(이것중 하나라도 잇으면)의 경우 Role_의 접두사를 앞에 붙여준다
    @ResponseStatus(HttpStatus.OK)
    public User getUserInfo(@PathVariable String userName) {
        Optional<User> optionalUser = userService.getUserWithAuthorities(userName);
        return optionalUser.orElseThrow(() -> new RuntimeException("해당 유저의 정보가 없습니다."));
    }
}
