package study.jdnc7.homeworkproject.domain.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.ibatis.type.Alias;
import org.springframework.security.core.GrantedAuthority;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Alias("Authority")
public class Authority implements GrantedAuthority {
    private static final String ROLE_ADMIN= "ROLE_ADMIN";
    private static final String ROLE_USER= "ROLE_USER";

    private String authorityName;

    public static Authority getUserAuthority() {
        return new Authority(ROLE_USER);
    }

    public static Authority getAdminAuthority() {
        return new Authority(ROLE_ADMIN);
    }

    @Override
    public boolean equals(Object obj) {
        return authorityName.equals(((Authority) obj).getAuthorityName());
    }

    @Override
    public String getAuthority() {
        return authorityName;
    }
}
