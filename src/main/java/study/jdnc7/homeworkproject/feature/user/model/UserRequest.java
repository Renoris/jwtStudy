package study.jdnc7.homeworkproject.feature.user.model;

import lombok.*;
import study.jdnc7.homeworkproject.domain.user.model.Authority;
import study.jdnc7.homeworkproject.domain.user.model.User;

import java.util.HashSet;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
    private String userName;
    private String password;
    private String nickName;

    public User toEntity() {
        return User.toEntity(
                this.userName,
                this.password,
                this.nickName,
                Authority.getUserAuthority()
        );

    }
}
