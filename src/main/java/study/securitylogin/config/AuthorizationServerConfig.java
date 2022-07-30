package study.securitylogin.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import study.securitylogin.domain.Role;
import study.securitylogin.service.CustomOauth2UserService;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class AuthorizationServerConfig {

    private final CustomOauth2UserService customOauth2UserService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(11);
    }

    @Bean
    public SecurityFilterChain authServerSecurityFilterChain(HttpSecurity http) throws Exception{
//        OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);
        http.csrf().disable();

        http.authorizeRequests()
                .antMatchers("/register/**").authenticated()
                .antMatchers("/admin/**").hasAnyRole((Role.ADMIN.getKey()))
                .antMatchers("/my/**").hasAnyRole(Role.MEMBER.getKey(), Role.ADMIN.getKey())
                .anyRequest().permitAll();

        http.formLogin()
                .loginPage("/login-page")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/home");

        http.oauth2Login()
                .loginPage("/login-page")
                .userInfoEndpoint()
                .userService(customOauth2UserService);

        return http.build();
    }
}
