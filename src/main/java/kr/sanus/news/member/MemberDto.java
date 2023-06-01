package kr.sanus.news.member;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberDto {

    private Long id;

    @NotEmpty(message = "이메일은 입력하세요.")
    @Email
    private String email;

    private String password;
    private String password2;

    @NotEmpty(message = "이름을 입력하세요.")
    private String name;

    @NotEmpty(message = "휴대폰 번호를 입력하세요.")
    private String mobile;

    public MemberEntity toEntity() {
        return MemberEntity.builder()
            .id(id)
            .email(email)
            .password(password)
            .name(name)
            .mobile(mobile)
            .build();
    }
}
