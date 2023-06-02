package kr.sanus.news.member;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public void join(MemberDto memberDto) {
        MemberEntity memberEntity = memberDto.toEntity();
        memberEntity.setPassword(passwordEncoder.encode(memberEntity.getPassword()));
        memberRepository.save(memberEntity);
    }

    public MemberDto findByEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return memberRepository.findByEmail(email).get().toDto();
    }

}
