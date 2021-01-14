package dev.jh.joo.mockito.member;

import dev.jh.joo.mockito.domain.Member;

import java.util.Optional;

public interface MemberService {
    Optional<Member> findById(Long memberId);
}
