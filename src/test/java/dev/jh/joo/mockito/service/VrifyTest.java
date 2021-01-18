package dev.jh.joo.mockito.service;

import dev.jh.joo.mockito.domain.Member;
import dev.jh.joo.mockito.domain.Study;
import dev.jh.joo.mockito.member.MemberService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class VrifyTest {

    @Mock
    MemberService memberService;
    @Mock
    StudyRepository studyRepository;

    @Test
    @DisplayName("메소드 호출 확인 및 메소드 호출 순서 확인 가능")
    void verifyTest01(){
        // given 주어진 상황
        StudyService studyService = new StudyService(memberService, studyRepository);
        assertNotNull(studyService);

        Member member = new Member();
        member.setId(1L);
        member.setEmail("dev.jh.joo@gmail.com");

        Study study = new Study(10, "BDD");

        when(memberService.findById(1L)).thenReturn(Optional.of(member));
        when(studyRepository.save(study)).thenReturn(study);

        studyService.createNewStudy(1L, study);

        assertEquals(member, study.getOwner());

        // 호출했는지 확인하는 방법
        // memberService에서 딱1번 notify(study) 가 호출 됐는지 확인 가능
        verify(memberService, times(1)).notify(study);
        // memberService에서 .validate가 절대 호출돼지 않는지 확인
        verify(memberService, never()).validate(any());

        // 순서를 확인 하고 싶을때는
        // study로 notify 하고나서 member로 notify를 한다.
        InOrder inOrder = inOrder(memberService);
        inOrder.verify(memberService).notify(study);
        inOrder.verify(memberService).notify(member);
    }

    @Test
    @Disabled
    @DisplayName(" No more interactions 테스트/ 해당 테스트는 실패 ")
    void verifyTest02(){
        // given 주어진 상황
        StudyService studyService = new StudyService(memberService, studyRepository);
        assertNotNull(studyService);

        Member member = new Member();
        member.setId(1L);
        member.setEmail("dev.jh.joo@gmail.com");

        Study study = new Study(10, "BDD");

        when(memberService.findById(1L)).thenReturn(Optional.of(member));
        when(studyRepository.save(study)).thenReturn(study);

        studyService.createNewStudy(1L, study);

        assertEquals(member, study.getOwner());


        // study로 notify 호출 이후에 아무 interaction도 일어나지 말아야 한다.
        // 실제 소스에서는 .notify(study) 이후에 .notify(member) 를 호출하기때문에 테스트 실패
        verify(memberService, times(1)).notify(study);
        verifyNoMoreInteractions(memberService);
    }


}
