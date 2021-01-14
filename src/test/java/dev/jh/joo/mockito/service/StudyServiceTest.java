package dev.jh.joo.mockito.service;

import dev.jh.joo.mockito.domain.Member;
import dev.jh.joo.mockito.member.MemberService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

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



}