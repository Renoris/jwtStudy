package study.jdnc7.homeworkproject.feature.user.model.dto;

import lombok.*;

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
