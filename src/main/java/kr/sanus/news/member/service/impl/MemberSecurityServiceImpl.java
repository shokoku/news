package kr.sanus.news.member.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import kr.sanus.news.member.data.MemberRole;
import kr.sanus.news.member.data.entity.MemberEntity;
import kr.sanus.news.member.data.repository.MemberRepository;

@RequiredArgsConstructor
@Service
public class MemberSecurityServiceImpl implements UserDetailsService {

  private final MemberRepository memberRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<MemberEntity> _memberEntity = memberRepository.findByEmail(username);
    if (_memberEntity.isEmpty()) {
      throw new UsernameNotFoundException("사용자를 찾을수 없습니다.");
    }
    MemberEntity memberEntity = _memberEntity.get();
    List<GrantedAuthority> authorities = new ArrayList<>();
    if ("admin@sanus.kr".equals(username)) {
      authorities.add(new SimpleGrantedAuthority(MemberRole.ADMIN.getValue()));
    } else {
      authorities.add(new SimpleGrantedAuthority(MemberRole.USER.getValue()));
    }
    return new User(memberEntity.getEmail(), memberEntity.getPassword(), authorities);
  }
}
