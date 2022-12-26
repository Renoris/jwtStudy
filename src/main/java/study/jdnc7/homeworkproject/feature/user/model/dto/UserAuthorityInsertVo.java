package study.jdnc7.homeworkproject.feature.user.model.dto;


import lombok.*;
import org.apache.ibatis.type.Alias;

@Alias("UserAuthorityInsertVo")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class UserAuthorityInsertVo {
    private Long userId;
    private String authorityName;
}
