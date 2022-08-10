package study.securitylogin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import study.securitylogin.dto.AppUserDto;
import study.securitylogin.service.CustomUserDetailsService;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final CustomUserDetailsService userService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/join")
    public UUID join(AppUserDto dto) {
        String encPassword = passwordEncoder.encode(dto.getPassword());
        return userService.saveUser(dto);
    }

    @GetMapping("/")
    public String loginPage() {
        return "/login-page";
    }

}
