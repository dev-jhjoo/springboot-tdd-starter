package dev.jh.joo.mockito.service;

import dev.jh.joo.mockito.domain.Member;
import dev.jh.joo.mockito.domain.Study;
import dev.jh.joo.mockito.member.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class BDDTest {
    // BDD란 에플리케이션이 어떻게 행동해야하는지에 대해서 공통된 이해를 구성하는 방식으로, TDD에서 창안

    @Mock
    MemberService memberService;
    @Mock
    StudyRepository studyRepository;

    @Test
    @DisplayName("bdd 간략 샘플")
    void bddTest(){
        /* given 주어진 상황 */
        StudyService studyService = new StudyService(memberService, studyRepository);
        assertNotNull(studyService);

        Member member = new Member();
        member.setId(1L);
        member.setEmail("dev.jh.joo@gmail.com");

        Study study = new Study(10, "BDD");
        // bdd 에서는 given인데 보기 어색하다 그래서 BDDMockito에서 제공하는 given으로 바꿀 수 있다.
//        when(memberService.findById(1L)).thenReturn(Optional.of(member));
//        when(studyRepository.save(study)).thenReturn(study);
        given(memberService.findById(1L)).willReturn(Optional.of(member));
        given(studyRepository.save(study)).willReturn(study);

        /* when 무언가를 하면 */
        studyService.createNewStudy(1L, study);

        /* then 이럴 것이다. */
        assertEquals(member, study.getOwner());
        // 호출했는지 확인하는 방법
        // memberService에서 딱1번 notify(study) 가 호출 됐는지 확인 가능
//        verify(memberService, times(1)).notify(study);
        then(memberService).should(times(1)).notify(study);

        // memberService에서 .validate가 절대 호출돼지 않는지 확인
//        verify(memberService, never()).validate(any());
        then(memberService).should(never()).validate(any());

    }
}
