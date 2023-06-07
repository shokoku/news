package kr.sanus.news.member.service;

import kr.sanus.news.member.data.dto.MemberDto;

public interface MemberService {

  void join(MemberDto memberDto);

  MemberDto findByEmail();

  void update(MemberDto memberDto);
}
