package kr.sanus.news.member.data.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.sanus.news.member.data.entity.MemberEntity;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

  Optional<MemberEntity> findByEmail(String email);
}
