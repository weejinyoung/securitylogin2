package study.securitylogin.service;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import study.securitylogin.domain.AppUser;
import study.securitylogin.domain.Role;
import study.securitylogin.dto.TestUserDto;
import study.securitylogin.repository.UserRepository;

import java.util.UUID;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class CustomUserDetailsServiceTest {

    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @Test
    void saveUserTest() {
        TestUserDto dto = TestUserDto.builder().
                oAuth2Id("isk06277").
                email("a8118199@gmail.com").
                password("004637").
                introduction("hi").
                nickname("wee").
                profileImageUrl("ewadsawf").
                role(Role.ADMIN).
                build();

        UUID saveUserUUID = customUserDetailsService.saveUserTest(dto);
        AppUser findUser = (AppUser) customUserDetailsService.loadUserByUsername("a8118199@gmail.com");
        Assertions.assertThat(saveUserUUID).isEqualTo(findUser.getId());
        System.out.println("와 pk는 = " + saveUserUUID.toString());
//        log.info(saveUserUUID.toString());
    }
}