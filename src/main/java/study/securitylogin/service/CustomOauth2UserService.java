package study.securitylogin.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.securitylogin.domain.AppUser;
import study.securitylogin.repository.UserRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomOauth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;


    @Override
    //구글로그인이 완료된 후 후처리하는 함수 : request에 엑세스토큰과 사용자프로필이 담, 코드를 받는게 아님
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        AppUser googleLoginUser = AppUser.builder()

                .build();

        userRepository.save(googleLoginUser);
        return super.loadUser(userRequest);
    }
}
