package study.jdnc7.homeworkproject.security.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import study.jdnc7.homeworkproject.domain.user.model.User;
import study.jdnc7.homeworkproject.domain.user.mapper.UserMapper;

import java.util.List;
import java.util.stream.Collectors;

@Component("userDetailsService")
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserMapper userMapper;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        return userMapper.findByUserNameWithAuthority(userName)
                .map(user -> createUser(userName, user))
                .orElseThrow(() -> new UsernameNotFoundException(userName + " -> 데이터베이스에서 찾을 수 없습니다"));
    }

    private org.springframework.security.core.userdetails.User createUser(String userName, User user) {
        if (!user.isActivated()) {
            throw new RuntimeException(userName + " -> 활성화 되어 있지 않습니다");
        }

        List<GrantedAuthority> grantedAuthorities = user.getAuthorities().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getAuthorityName()))
                .collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(user.getUserName(),
                user.getPassword(),
                grantedAuthorities);
    }
}
