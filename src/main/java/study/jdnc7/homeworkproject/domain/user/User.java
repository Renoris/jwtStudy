package study.jdnc7.homeworkproject.domain.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private Long userId;
    private String username;
    private String password;
    private String nickname;
    private boolean activated;
    private Set<Authority> authorities;

}
