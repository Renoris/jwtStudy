package study.jdnc7.homeworkproject.feature.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.jdnc7.homeworkproject.domain.user.model.Authority;
import study.jdnc7.homeworkproject.domain.user.model.User;
import study.jdnc7.homeworkproject.domain.user.mapper.UserAuthorityMapper;
import study.jdnc7.homeworkproject.domain.user.mapper.UserMapper;
import study.jdnc7.homeworkproject.feature.user.model.UserAuthorityInsertVo;
import study.jdnc7.homeworkproject.feature.user.model.UserRequest;
import study.jdnc7.homeworkproject.util.SecurityUtil;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserAuthorityMapper userAuthorityMapper;

    @Transactional
    public Long signup(UserRequest userRequest) {
        if (userMapper.findByUserNameWithAuthority( userRequest.getUserName()).orElse(null) != null) {
            throw new RuntimeException("이미 가입되어 있는 유저입니다.");
        }

        Authority authority = Authority.getUserAuthority();
        User user = User.toEntity(
                userRequest.getUserName(),
                passwordEncoder.encode(userRequest.getPassword()),
                userRequest.getNickName(),
                authority
        );
        userMapper.insert(user);
        if (user.getUserId() == null) throw new RuntimeException("유저가 데이터베이스에 저장되지 않았습니다.");
        userAuthorityMapper.insert(new UserAuthorityInsertVo(user.getUserId(), authority.getAuthorityName()));

        return user.getUserId();
    }

    @Transactional(readOnly = true)
    public Optional<User> getUserWithAuthorities(String userName) {
        return userMapper.findByUserNameWithAuthority(userName);
    }

    @Transactional(readOnly = true)
    public Optional<User> getMyUserWithAuthorities() {
        return SecurityUtil.getCurrentUsername().flatMap(userMapper::findByUserNameWithAuthority);
    }
}
