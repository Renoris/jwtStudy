package study.jdnc7.homeworkproject.feature.user.controller;

import org.apache.ibatis.annotations.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class LoginController {

    @GetMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public String hello () {
        return "hello";
    }
}
