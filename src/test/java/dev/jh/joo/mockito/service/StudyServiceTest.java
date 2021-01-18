package dev.jh.joo.mockito.service;

import dev.jh.joo.mockito.domain.Member;
import dev.jh.joo.mockito.domain.Study;
import dev.jh.joo.mockito.member.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StudyServiceTest {

    // 의존성 주입 방식 3가지

    // @Mock 을 사용하려면 MockitoExtension.claa 를 Extension 해야함
    @Mock
    MemberService service;
    @Mock
    StudyRepository repository;

    @Test
    void createStudyService1(){
        StudyService studyService = new StudyService(service, repository);
        assertNotNull(studyService);
    }

    // @Mock 은 파라미터 형태로도 사용 가능
    @Test
    void createStudyService2(
            @Mock MemberService serviceTest,
            @Mock StudyRepository repositoryTest
    ){
        StudyService studyService = new StudyService(serviceTest, repositoryTest);
        assertNotNull(studyService);
    }

    // Mockito.mock() 이라는 함수를 통해서도 생성 가능
    @Test
    void createStudyService3(){
        MemberService memberService = mock(MemberService.class);
        StudyRepository studyRepository = mock(StudyRepository.class);
        StudyService studyService = new StudyService(memberService, studyRepository);
        assertNotNull(studyService);
    }

    @Test
    @DisplayName("Mock Stubbing 테스트")
    void stubbingTest(
            @Mock MemberService service,
            @Mock StudyRepository repository
    ){

        Member member = Member.builder()
                .id(1L)
                .email("dev.jh.joo@gmail.com")
                .build();
//        when(service.findById(1L)).thenReturn(Optional.of(member));

        //반복적으로 사용 가능.
        when(service.findById(1L))
                .thenReturn(Optional.of(member)) // 첫번째
                .thenThrow(new RuntimeException()) // 두번째
                .thenReturn(Optional.empty()); // 세번째

        Study study = new Study(10, "java");
        StudyService studyService = new StudyService(service, repository);

        studyService.createNewStudy(1L, study);
        assertThrows(RuntimeException.class, () -> service.findById(1L));
        assertEquals(Optional.empty(), service.findById(1L));
    }



}