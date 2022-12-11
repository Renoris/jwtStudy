package study.jdnc7.homeworkproject.feature.user.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.ibatis.type.Alias;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Alias("User")
public class User implements UserDetails {

    private Long userId;
    private String userName;
    private String password;
    private String nickName;
    private boolean activated;
    private Set<Authority> authorities;


    public static User toEntity(String userName, String password, String nickName, Authority authority) {
        Set<Authority> set = new HashSet<>();
        set.add(authority);
        return new User(
                null,
                userName,
                password,
                nickName,
                true,
                set
        );
    }

    public String getUserName() {
        return userName;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return activated;
    }
}
