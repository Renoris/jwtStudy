package study.jdnc7.homeworkproject.feature.user.model.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {

    @NotNull
    @Size(min = 3, max = 50)
    private String userName;

    @NotNull
    @Size(min = 3, max = 100)
    private String password;

}
