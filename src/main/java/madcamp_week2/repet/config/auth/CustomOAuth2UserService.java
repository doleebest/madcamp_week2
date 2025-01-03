package madcamp_week2.repet.config.auth;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import madcamp_week2.repet.Domain.User;
import madcamp_week2.repet.Repository.UserRepository;
import madcamp_week2.repet.config.auth.dto.OAuthAttributes;
import madcamp_week2.repet.config.auth.dto.SessionUser;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final UserRepository userRepository;
    private final HttpSession httpSession;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        // 로그인 진행 중인 서비스를 구분
        // 네이버로 로그인 진행 중인지, 구글로 로그인 진행 중인지, ... 등을 구분
        String registrationId = userRequest.getClientRegistration().getRegistrationId();

        // OAuth2 로그인 진행 시 키가 되는 필드 값(Primary Key와 같은 의미)
        // 구글의 경우 기본적으로 코드를 지원
        // 하지만 네이버, 카카오 등은 기본적으로 지원 X
        String userNameAttributeName = userRequest.getClientRegistration()
                .getProviderDetails()
                .getUserInfoEndpoint()
                .getUserNameAttributeName();

        // OAuth2UserService를 통해 가져온 OAuth2User의 attribute 등을 담을 클래스
        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());

        // 사용자 저장 또는 업데이트
        User user = saveOrUpdate(attributes);
        // 세션에 사용자 정보 저장


        httpSession.setAttribute("user", user);
        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(user.getRoleKey())),
                attributes.getAttributes(),
                attributes.getNameAttributeKey());
    }


    @Transactional
    public User saveOrUpdate(OAuthAttributes attributes) {
        String email = attributes.getEmail();
        System.out.println(email);
//        Optional<User> existingUser = userRepository.findByEmail(email);
//        System.out.println(existingUser.get().getEmail());
//        if (existingUser.isPresent()) {
//            // 기존 사용자 업데이트
//            System.out.println("기존 사용자 존재, 업데이트 시작...");
//            User updatedUser = existingUser.get().update(attributes.getName(), attributes.getPicture());
//            return updatedUser;
//        } else {
//            // 신규 사용자 추가
//            System.out.println("기존 사용자 없음, 새로운 사용자 삽입...");
//            User newUser = attributes.toEntity();
//            User savedUser = userRepository.save(newUser);
//            return savedUser;
//        }
        User user = userRepository.findByEmail(attributes.getEmail())
                // 구글 사용자 정보 업데이트(이미 가입된 사용자) => 업데이트
//                .map(entity -> entity.update(attributes.getName(), attributes.getPicture()))
                // 가입되지 않은 사용자 => User 엔티티 생성
                //.orElse(attributes.toEntity());
                .orElseGet(() -> {
                    System.out.println("새로운 사용자 삽입");
                    User newUser = attributes.toEntity();
                    System.out.println("새로 생성된 사용자: " + newUser);
                    return userRepository.save(newUser);                });
        return user; // 안됐던 이유 : pk 값은 Id, 근데 바인딩해야했던 값은 email (그래서 User에서 Pk 값을 다시 email로 해줬더니 해결!)
    }


}
