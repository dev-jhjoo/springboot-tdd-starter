package dev.jh.joo.mockito.member;

import dev.jh.joo.mockito.domain.Member;
import dev.jh.joo.mockito.domain.Study;

import java.util.Optional;

public interface MemberService {

    Optional<Member> findById(Long memberId);

    void validate(Long memberId);

    void notify(Study study);

    void notify(Member member);
}
