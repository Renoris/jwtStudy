package study.jdnc7.homeworkproject.util;

import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import study.jdnc7.homeworkproject.feature.user.model.entity.Authority;
import study.jdnc7.homeworkproject.feature.user.model.entity.User;

import java.util.Optional;


@NoArgsConstructor
public class SecurityUtil {
    private static final Logger logger = LoggerFactory.getLogger(SecurityUtil.class);

    public static Optional<String> getCurrentUsername() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //홀딩되는 시점은 jwtFilter에서 request가 들어올때 authentication 객체를 저장해서 사용하게 됨
        if (authentication == null) {
            logger.debug("Security Context에 인증 정보가 없습니다");
            return Optional.empty();
        }

        String userName = null;
        if (authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            userName = userDetails.getUsername();
         } else if (authentication.getPrincipal() instanceof String) {
            userName = (String) authentication.getPrincipal();
        }

        return Optional.ofNullable(userName);
    }

    public static Optional<Long> getCurrentUserId() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //홀딩되는 시점은 jwtFilter에서 request가 들어올때 authentication 객체를 저장해서 사용하게 됨
        if (authentication == null) {
            logger.debug("Security Context에 인증 정보가 없습니다");
            return Optional.empty();
        }

        Long userId = null;
        if (authentication.getPrincipal() instanceof UserDetails) {
            User user = (User) authentication.getPrincipal();
            userId = user.getUserId();
        }

        return Optional.ofNullable(userId);
    }

    public static boolean isAdmin() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //홀딩되는 시점은 jwtFilter에서 request가 들어올때 authentication 객체를 저장해서 사용하게 됨
        if (authentication == null) {
            logger.debug("Security Context에 인증 정보가 없습니다");
            return false;
        }

        if (authentication.getPrincipal() instanceof UserDetails) {
            User user = (User) authentication.getPrincipal();
            return user.getAuthorities().contains(Authority.getAdminAuthority());
        }

        return false;
    }
}
