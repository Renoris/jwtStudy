package study.jdnc7.homeworkproject.feature.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class LoginController {

    @GetMapping("/login")
    public ResponseEntity<String> hello () {
        return ResponseEntity.ok("hello");
    }
}
