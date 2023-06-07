package kr.sanus.news.member.service.impl;

import java.util.Optional;

import kr.sanus.news.member.service.MemberService;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import kr.sanus.news.member.data.dto.MemberDto;
import kr.sanus.news.member.data.entity.MemberEntity;
import kr.sanus.news.member.data.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

  private final MemberRepository memberRepository;
  private final PasswordEncoder passwordEncoder;

  @Override
  public void join(MemberDto memberDto) {
    MemberEntity memberEntity = memberDto.toEntity();
    memberEntity.setPassword(passwordEncoder.encode(memberEntity.getPassword()));
    memberRepository.save(memberEntity);
  }

  @Override
  public MemberDto findByEmail() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String email = authentication.getName();
    Optional<MemberEntity> memberOptional = memberRepository.findByEmail(email);
    if (memberOptional.isPresent()) {
      return memberOptional.get().toDto();
    }
    return null;
  }

  @Override
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
