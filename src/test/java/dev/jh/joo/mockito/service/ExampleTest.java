package dev.jh.joo.mockito.service;

import dev.jh.joo.mockito.domain.Member;
import dev.jh.joo.mockito.domain.Study;
import dev.jh.joo.mockito.member.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ExampleTest {

    @Mock MemberService service;
    @Mock StudyRepository repository;


    @Test
    @DisplayName("Member 객체 stubbing 테스트")
    void findMember(){
        // given
        Member member = Member.builder().id(1L).email("dev.jh.joo@gmail.com").build();

        // when
        when(service.findById(1L)).thenReturn(Optional.of(member));
        Optional<Member> findMember = service.findById(1L);

        // then
        assertEquals("dev.jh.joo@gmail.com", findMember.get().getEmail());
    }

    @Test
    @DisplayName("Study save stubbing 테스트")
    void saveStudy(){
        // given
        Study study = new Study(1, "TDD");

        // when
        when(repository.save(study)).thenReturn(study);

        // then
        assertAll(
                () -> assertEquals(1, repository.save(study).getLimitCount()),
                () -> assertEquals("TDD", repository.save(study).getName())
        );
    }


}
