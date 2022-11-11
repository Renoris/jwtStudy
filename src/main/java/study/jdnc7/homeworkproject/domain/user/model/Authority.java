package study.jdnc7.homeworkproject.domain.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Alias("Authority")
public class Authority {
    private static final String ROLE_ADMIN= "ROLE_ADMIN";
    private static final String ROLE_USER= "ROLE_USER";

    private String authorityName;

    public static Authority getUserAuthority() {
        return new Authority(ROLE_USER);
    }

    public static Authority getAdminAuthority() {
        return new Authority(ROLE_ADMIN);
    }
}
