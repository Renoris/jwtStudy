package study.jdnc7.homeworkproject.feature.user.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

import javax.xml.ws.ServiceMode;

@Alias("UserAuthorityInsertVo")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class UserAuthorityInsertVo {
    private Long userId;
    private String authorityName;
}
