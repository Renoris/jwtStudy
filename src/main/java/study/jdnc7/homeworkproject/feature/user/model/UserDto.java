package study.jdnc7.homeworkproject.feature.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Alias("UserDto")
public class UserDto {
    private Long userId;
    private String userName;
    private String nickName;
}
