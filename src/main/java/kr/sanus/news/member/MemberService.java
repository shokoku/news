package kr.sanus.news.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public void join(MemberDto memberDto) {
        memberRepository.save(memberDto.toEntity());
    }

}
