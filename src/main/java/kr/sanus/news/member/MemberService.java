package kr.sanus.news.member;

import java.util.NoSuchElementException;
import java.util.Optional;
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
        Optional<MemberEntity> memberOptional = memberRepository.findByEmail(email);
        if (memberOptional.isPresent()) {
            return memberOptional.get().toDto();
        }
        return null;
    }

    public void update(MemberDto memberDto) {
        Optional<MemberEntity> memberOptional = memberRepository.findById(memberDto.getId());
        if (memberOptional.isPresent()) {
            MemberEntity memberEntity = memberOptional.get();
            memberEntity.setName(memberDto.getName());
            memberEntity.setMobile(memberDto.getMobile());
            memberRepository.save(memberEntity);
        }
    }
}
