package study.securitylogin.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import study.securitylogin.domain.AppUser;
import study.securitylogin.domain.Role;

@Data
@NoArgsConstructor
public class TestUserDto {

    private String oAuth2Id;
    private String email;
    private String nickname;
    private String profileImageUrl;
    private String introduction;
    private Role role;

    @Builder
    public TestUserDto(String oAuth2Id, String email, String nickname, String profileImageUrl, String introduction, Role role) {
        this.oAuth2Id = oAuth2Id;
        this.email = email;
        this.nickname = nickname;
        this.profileImageUrl = profileImageUrl;
        this.introduction = introduction;
        this.role = role;
    }

    public AppUser toEntity() {
        return AppUser.builder().
                email(this.email).
                oAuth2Id(this.oAuth2Id).
                nickname(this.email).
                profileImageUrl(this.profileImageUrl).
                introduction(this.introduction).
                role(this.role).build();
    }
}
