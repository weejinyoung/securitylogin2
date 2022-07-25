package study.securitylogin.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.securitylogin.domain.AppUser;
import study.securitylogin.dto.AppUserDto;
import study.securitylogin.dto.TestUserDto;
import study.securitylogin.repository.UserRepository;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<AppUser> appUser = userRepository.findByEmail(email);
        return appUser.orElseThrow(() ->new UsernameNotFoundException("유저를 찾을 수 없습니다."));
    }

    public UUID saveUserTest(TestUserDto dto) {
        AppUser appUser = userRepository.save(dto.toEntity());
        return appUser.getId();
    }

    public UUID saveUser(AppUserDto dto) {

        AppUser saveUser = userRepository.save(dto.toEntity());
        return saveUser.getId();
    }
}
