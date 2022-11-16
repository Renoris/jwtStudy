package study.jdnc7.homeworkproject.feature.user.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import study.jdnc7.homeworkproject.domain.user.model.Authority;
import study.jdnc7.homeworkproject.domain.user.model.User;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    @NotNull
    @Size(min = 3, max = 50)
    private String userName;

    @NotNull
    @Size(min = 3, max = 100)
    private String password;

    @NotNull
    @Size(min = 3, max = 50)
    private String nickName;

}
