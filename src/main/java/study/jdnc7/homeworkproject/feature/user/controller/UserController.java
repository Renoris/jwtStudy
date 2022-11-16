package study.jdnc7.homeworkproject.feature.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import study.jdnc7.homeworkproject.domain.user.model.User;
import study.jdnc7.homeworkproject.feature.user.model.UserDto;
import study.jdnc7.homeworkproject.feature.user.model.UserRequest;
import study.jdnc7.homeworkproject.feature.user.service.UserService;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<Long> signup(@Valid @RequestBody UserRequest userRequest) {
        return new ResponseEntity<>(userService.signup(userRequest), HttpStatus.CREATED);
    }

    @GetMapping("/my")
    public ResponseEntity<User> getMyUserInfo() {
        Optional<User> optionalUser = userService.getMyUserWithAuthorities();
        if (!optionalUser.isPresent()) throw new RuntimeException("로그인 하지 않았습니다");
        return new ResponseEntity<>(optionalUser.get(), HttpStatus.OK);
    }

    @GetMapping("/{userName}")
    @PreAuthorize("hasRole('ADMIN')") //앞에 hasRole(이것이 무조건 있어야한다)이나 hasAnyRole(이것중 하나라도 잇으면)의 경우 Role_의 접두사를 앞에 붙여준다
    public ResponseEntity<User> getUserInfo(@PathVariable String userName) {
        Optional<User> optionalUser = userService.getUserWithAuthorities(userName);
        if (!optionalUser.isPresent()) throw new RuntimeException("해당 유저를 찾지 못했습니다");
        return new ResponseEntity<>(optionalUser.get(), HttpStatus.OK);

    }
}
